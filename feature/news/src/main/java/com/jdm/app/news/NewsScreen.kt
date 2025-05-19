package com.jdm.app.news

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jdm.app.designsystem.component.ConfirmDialog
import com.jdm.app.designsystem.component.OutlineTextChip
import com.jdm.app.designsystem.component.PrimaryButton
import com.jdm.app.designsystem.component.SecondButton
import com.jdm.app.designsystem.component.UnderLineTextTab
import com.jdm.app.designsystem.component.UpDownArrowButton
import com.jdm.app.designsystem.theme.FusionTheme
import com.jdm.app.model.ChipState
import com.jdm.app.model.DialogToken
import com.jdm.app.model.News
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat


@Composable
fun NewsRoute(
    viewModel: NewsViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val newsLazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    BackHandler {
        onBack()
    }
    NewsScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(FusionTheme.colors.white),
        today = uiState.today,
        updateTime = uiState.updateTime,
        chipStates = uiState.category,
        newsList = uiState.filteredNews,
        updatedNewsCnt = uiState.updatedNewsCnt,
        newsLazyListState = newsLazyListState,
        onClickCategoryItem = { chipState ->
            viewModel.setEvent(NewsEvent.OnClickCategory(chipState))
        },
        onClickInitialize = {
            viewModel.setEvent(NewsEvent.OnClickInitialize)
        },
        onClickComplete = {
            viewModel.setEvent(NewsEvent.OnClickComplete(it))
        },
        onSubjectBottomDialogDismiss = {
            viewModel.setEvent(NewsEvent.OnSubjectBottomDialogDismiss)
        },
        onConfirmDialogDismiss = {
            viewModel.setEvent(NewsEvent.OnConfirmDialogDismiss)
        },
        onClickSubjectFilter = {
            viewModel.setEvent(NewsEvent.OnClickSubjectFilter)
        },
        onClickNewNews = {
            viewModel.setEvent(NewsEvent.OnClickNewNews)
        },
        subjectBottomDialogToken = uiState.subjectDialogToken,
        dialogToken = uiState.dialogToken
    )
    LaunchedEffect(Unit) {
        viewModel.initData()
    }
    LaunchedEffect(uiState.filteredNews.size) {
        coroutineScope.launch {
            newsLazyListState.scrollToItem(0)
            delay(1000L)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    newsLazyListState: LazyListState,
    today: String,
    updateTime: String,
    chipStates: List<ChipState>,
    newsList: List<News>,
    updatedNewsCnt: Int,
    subjectBottomDialogToken: SubjectDialogToken?,
    dialogToken: DialogToken?,
    onSubjectBottomDialogDismiss: () -> Unit,
    onConfirmDialogDismiss: () -> Unit,
    onClickCategoryItem: (ChipState) -> Unit,
    onClickInitialize: () -> Unit,
    onClickComplete: (List<ChipState>) -> Unit,
    onClickSubjectFilter: () -> Unit,
    onClickNewNews: () -> Unit
) {
    BasicNewsScreen(
        modifier = modifier,
        dateContents = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = today,
                style = FusionTheme.typography.title_m
            )
            Text(
                text = updateTime,
                style = FusionTheme.typography.text_xs
            )
        },
        categoryItems = {
            CategoryItems(
                items = chipStates,
                onClickItem = onClickCategoryItem
            )
        },
        newsContents = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                state = newsLazyListState
            ) {
                NewsItems(
                    items = newsList,
                    onClickItem = {}
                )
            }
        },
        filterContents = {
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        onClickSubjectFilter()
                    },
                painter = painterResource(R.drawable.ic_filter_black),
                contentDescription = "filter icon"
            )
        },
        updatedNewsFloatingContents = {
            if (updatedNewsCnt > 0) {
                Row(
                    modifier = Modifier
                        .width(120.dp)
                        .height(36.dp)
                        .background(
                            shape = RoundedCornerShape(16.dp),
                            color = FusionTheme.colors.green400
                        )
                        .clickable {
                            onClickNewNews()
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier
                            .size(16.dp),
                        painter = painterResource(R.drawable.ic_arrow_up_white),
                        contentDescription = "up arrow white"
                    )
                    Spacer(
                        modifier = Modifier
                            .width(8.dp)
                    )
                    Text(
                        text = stringResource(R.string.str_new_news),
                        style = FusionTheme.typography.text_xxs.copy(color = FusionTheme.colors.white)
                    )
                }
            }
        },
        subjectBottomDialog = {
            if (subjectBottomDialogToken != null) {
                var subjectsState by remember {
                    mutableStateOf<List<ChipState>>(
                        subjectBottomDialogToken.subjects
                    )
                }
                ModalBottomSheet(
                    modifier = Modifier,
                    dragHandle = null,
                    sheetState = rememberModalBottomSheetState(
                        skipPartiallyExpanded = false
                    ),
                    onDismissRequest = onSubjectBottomDialogDismiss,
                    properties = ModalBottomSheetDefaults.properties(shouldDismissOnBackPress = false)
                ) {
                    BasicSubjectFilterScreen(
                        modifier = Modifier
                            .background(
                                shape = RoundedCornerShape(
                                    topEnd = 8.dp,
                                    topStart = 8.dp
                                ),
                                color = FusionTheme.colors.white
                            ),
                        closeContents = {
                            Image(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        onSubjectBottomDialogDismiss()
                                    },
                                painter = painterResource(R.drawable.ic_close_black),
                                contentDescription = "close black"
                            )
                        },
                        chipGroupContents = {
                            OutlineTextChipGroup(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 20.dp
                                    ),
                                chips = subjectsState,
                                textStyle = FusionTheme.typography.text_m,
                                onClickChip = { clickedChipState ->
                                    subjectsState = subjectsState.map {
                                        if (it.id == clickedChipState.id) {
                                            it.copy(isChecked = !clickedChipState.isChecked)
                                        } else
                                            it.copy()
                                    }
                                }
                            )
                        },
                        buttonContents = {
                            SecondButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .weight(1f),
                                text = stringResource(R.string.str_initialization),
                                onClick = onClickInitialize,
                            )
                            Spacer(
                                modifier = Modifier
                                    .width(12.dp)
                            )
                            PrimaryButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .weight(1f),
                                text = stringResource(R.string.str_select_complete),
                                onClick = {
                                    onClickComplete(subjectsState)
                                }
                            )
                        }
                    )
                }
            }
        },
        dialogContents = {
            if (dialogToken != null) {
                ConfirmDialog(
                    title = dialogToken.title,
                    rightText = stringResource(dialogToken.rightTextRes),
                    onDismiss = onConfirmDialogDismiss,
                    onClickButton = onConfirmDialogDismiss,
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicNewsScreen(
    modifier: Modifier,
    dateContents: @Composable RowScope.() -> Unit,
    categoryItems: LazyListScope.() -> Unit,
    newsContents: @Composable () -> Unit,
    filterContents: @Composable RowScope.() -> Unit,
    updatedNewsFloatingContents: @Composable () -> Unit,
    subjectBottomDialog: @Composable () -> Unit,
    dialogContents: @Composable () -> Unit
) {
    Box(
        modifier = modifier
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 20.dp,
                        start = 20.dp,
                        end = 20.dp
                    ),
                verticalAlignment = Alignment.Bottom
            ) {
                dateContents()
            }
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(
                            Alignment.CenterStart
                        )
                        .padding(
                            end = 60.dp
                        ),
                    contentPadding = PaddingValues(
                        horizontal = 20.dp
                    )
                ) {
                    categoryItems()
                }
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val leftRightFade = Brush
                        .horizontalGradient(
                            0f to Color.Transparent,
                            0.5f to FusionTheme.colors.gray300,
                            1f to FusionTheme.colors.white
                        )
                    Box(
                        modifier = Modifier
                            .fadingEdge(leftRightFade)
                            .background(Color.White)
                            .size(48.dp)
                    )
                    filterContents()
                    Spacer(
                        modifier = Modifier
                            .width(20.dp)
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    thickness = 1.dp,
                    color = FusionTheme.colors.gray300
                )

            }
            newsContents()
        }
        Column(
            modifier = Modifier
                .align(
                    Alignment.TopCenter
                )
        ) {
            Spacer(
                modifier = Modifier
                    .height(160.dp)
            )

            updatedNewsFloatingContents()


        }
        subjectBottomDialog()
        dialogContents()
    }
}

fun LazyListScope.CategoryItems(
    items: List<ChipState>,
    onClickItem: (ChipState) -> Unit
) {
    itemsIndexed(
        items = items, key = { index, item -> item.id }
    ) { index, item ->
        UnderLineTextTab(
            modifier = Modifier
                .height(
                    48.dp
                ),
            text = stringResource(item.title),
            isChecked = item.isChecked,
            textStyle = FusionTheme.typography.title_s,
            onClick = {
                onClickItem(item)
            }
        )

    }
}

fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }


fun LazyListScope.NewsItems(
    items: List<News>,
    onClickItem: (News) -> Unit
) {
    itemsIndexed(
        items = items, key = { index, item -> item.id }
    ) { index, item ->
        Row {
            NewsItemScreen(
                modifier = Modifier
                    .fillMaxWidth(),
                id = item.id,
                title = item.title,
                time = item.date,
                category = item.category,
                subject = item.subject,
                source = item.source,
                content = item.content,
                isNew = item.isNew
            )
        }

    }
}

@Composable
fun NewsItemScreen(
    modifier: Modifier,
    id: Int,
    title: String,
    time: Long,
    category: String,
    subject: String,
    source: String,
    content: String,
    isNew: Boolean
) {
    val timeFormat = SimpleDateFormat("HH:mm:ss")
    var isExpand by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .clickable {
                isExpand = !isExpand
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "[${category}]",
                        style = FusionTheme.typography.text_xs.copy(FusionTheme.colors.gray600),
                        maxLines = 1
                    )
                    Spacer(
                        modifier = Modifier
                            .width(4.dp)
                    )
                    if (isNew) {
                        Text(
                            text = stringResource(R.string.str_new),
                            style = FusionTheme.typography.title_xs.copy(FusionTheme.colors.red400),
                            maxLines = 1
                        )
                    }
                }

                Text(
                    text = "${title}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp
                        ),
                    style = FusionTheme.typography.title_s,
                    overflow = if (isExpand) TextOverflow.Visible else TextOverflow.Ellipsis,
                    maxLines = 1
                )

                OutlineTextChip(
                    modifier = Modifier
                        .padding(
                            start = 20.dp,
                            top = 8.dp
                        ),
                    text = subject,
                    textStyle = FusionTheme.typography.text_xxs,
                    isChecked = false,
                    horizontalPadding = 6.dp,
                    onClick = {}
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 8.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = timeFormat.format(time),
                        style = FusionTheme.typography.text_xxs
                    )
                    Spacer(
                        modifier = Modifier
                            .width(4.dp)
                    )

                    Text(
                        text = source,
                        style = FusionTheme.typography.text_xxs
                    )
                }
            }
            Column(
                modifier = Modifier
                    .width(56.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UpDownArrowButton(
                    modifier = Modifier
                        .size(16.dp),
                    checked = isExpand,
                    onCheckedChange = { }
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = if (isExpand) 20.dp else 0.dp
                ),
            thickness = 1.dp,
            color = FusionTheme.colors.gray300
        )
        AnimatedVisibility(
            isExpand
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 16.dp,
                            horizontal = 16.dp
                        ),
                    text = content,
                    style = FusionTheme.typography.text_xs
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth(),
                    thickness = 1.dp,
                    color = FusionTheme.colors.gray300
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OutlineTextChipGroup(
    modifier: Modifier = Modifier,
    chips: List<ChipState>,
    textStyle: TextStyle,
    onClickChip: (ChipState) -> Unit
) {
    FlowRow(
        modifier = modifier
    ) {

        chips.forEachIndexed { index, chipState ->
            OutlineTextChip(
                modifier = Modifier
                    .padding(
                        horizontal = 8.dp,
                        vertical = 4.dp
                    ),
                text = stringResource(chipState.title),
                isChecked = chipState.isChecked,
                textStyle = textStyle,
                onClick = {
                    onClickChip(chipState)

                },
            )
        }
    }
}

@Composable
fun BasicSubjectFilterScreen(
    modifier: Modifier = Modifier,
    closeContents: @Composable RowScope.() -> Unit,
    chipGroupContents: @Composable () -> Unit,
    buttonContents: @Composable RowScope.() -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 20.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            closeContents()
        }
        chipGroupContents()
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(
                    horizontal = 20.dp,
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            buttonContents()
        }
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SubjectBottomDialogScreen() {
    FusionTheme {
        OutlineTextChipGroup(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            chips = listOf(
                ChipState("미국주식", R.string.str_subject_01, true),
                ChipState("한국 주식", R.string.str_subject_02, false),
                ChipState("아시아 주식", R.string.str_subject_03, false),
                ChipState("유럽 주식", R.string.str_subject_04, false),
                ChipState("미국 경제지표", R.string.str_subject_05, false),
                ChipState("한국 경제지표", R.string.str_subject_06, false),
                ChipState("아시아 경제지표", R.string.str_subject_07, false),
                ChipState("유럽 경제지표", R.string.str_subject_08, false),
                ChipState("시장 분석", R.string.str_subject_09, false),
                ChipState("지정학", R.string.str_subject_10, false),
                ChipState("무역", R.string.str_subject_11, false),
                ChipState("에너지", R.string.str_subject_12, false),

                ),
            onClickChip = {},
            textStyle = FusionTheme.typography.text_m,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewsItemScreen(

) {
    FusionTheme {
        NewsItemScreen(
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp),
            id = 1,
            title = "한국 주식 상승 마감",
            time = 0,
            category = "경제발표",
            subject = "미국 경제지표",
            source = "연준 보고서",
            content = "미국 경제지표 관련 최신 소비자물가지수(CPI)가 최근 발표되어, 전월 대비 0.4% 소폭 증가하며 시장 컨센서스에 부합했습니다. 이는 현재 거시경제 상황에 대한 안정적인 시그널로 해석됩니다.",
            true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewsScreen(

) {
    FusionTheme {
        NewsScreen(
            modifier = Modifier
                .fillMaxSize(),
            today = "2025년 05월 13일",
            updateTime = "2025-05-13 14:00:00",
            chipStates = listOf(
                ChipState("전체", R.string.str_all, false),
                ChipState("중요", R.string.str_importance, false),
                ChipState("주식", R.string.str_stock, false),
                ChipState("경제 발표", R.string.str_economic_presentation, false),
                ChipState("뉴스", R.string.str_news, false)
            ),
            newsList = listOf(),
            onClickComplete = {},
            onClickInitialize = {},
            onClickSubjectFilter = {},
            onSubjectBottomDialogDismiss = {},
            onClickCategoryItem = {},
            updatedNewsCnt = 1,
            subjectBottomDialogToken = null,
            onClickNewNews = {},
            newsLazyListState = rememberLazyListState(),
            dialogToken = null,
            onConfirmDialogDismiss = {}
        )
    }
}