package com.gmail.jaycastro100.us.mtgcardrulings.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.gmail.jaycastro100.us.mtgcardrulings.data.model.rulingsresponse.RulingsResponseData
import com.gmail.jaycastro100.us.mtgcardrulings.ui.theme.BackgroundGray

@Composable
fun DetailsScreen(
    viewModel : DetailsScreenViewModel = hiltViewModel(),
    cardId : String
) {

    LaunchedEffect(
        key1 = cardId
    ) {

        viewModel.getSingleCardData(cardId)
    }

    val detailsState by remember { mutableStateOf(viewModel.viewState) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        content = {

            if (detailsState.value.loading) {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize()
                )
            } else {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .background(BackgroundGray),
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        //Cards have different layouts, some have 2 sides
                        when (detailsState.value.screenInfo?.layoutType) {

                            "transform" -> {
                                TransformLayoutCard(
                                    modifier = Modifier.weight(
                                        3f,
                                        false
                                    ),
                                    model = if (detailsState.value.isShowingFrontSide) {
                                        detailsState.value.screenInfo?.twoSidedCardLayoutFrontImage
                                    } else {
                                        detailsState.value.screenInfo?.twoSidedCardLayoutBackImage
                                    },
                                    onClick = { viewModel.flipCard() }
                                )
                            }

                            "modal_dfc" -> {
                                TransformLayoutCard(
                                    modifier = Modifier.weight(
                                        3f,
                                        false
                                    ),
                                    model = if (detailsState.value.isShowingFrontSide) {
                                        detailsState.value.screenInfo?.twoSidedCardLayoutFrontImage
                                    } else {
                                        detailsState.value.screenInfo?.twoSidedCardLayoutBackImage
                                    },
                                    onClick = { viewModel.flipCard() }
                                )
                            }
                            else        -> {
                                NormalLayoutCard(
                                    modifier = Modifier.weight(
                                        3f,
                                        false
                                    ),
                                    model = detailsState.value.screenInfo?.singleSidedCardLayoutImage,
                                )
                            }
                        }
                        Column(
                            modifier = Modifier
                                .widthIn()
                                .weight(
                                    1f,
                                    false
                                )
                        ) {
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Legal in: ",
                                color = Color.Red,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(
                                    top = 16.dp,
                                    end = 8.dp
                                )
                            )
                            detailsState.value.screenInfo?.legalities?.let { legalities ->
                                LegalitiesColumn(
                                    legalities = legalities,
                                    modifier = Modifier
                                )
                            }
                        }
                    }

                    Text(
                        text = "Rulings",
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold
                    )
                    Card(
                        modifier = Modifier.padding(8.dp)
                    ) {

                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                        ) {

                            if (detailsState.value.screenInfo?.rulings?.isEmpty() == true) {
                                Text(
                                    text = "No additional rulings exist for this card.",
                                    modifier = Modifier.fillMaxWidth()
                                )
                            } else {
                                detailsState.value.screenInfo?.rulings?.forEach { item ->
                                    RulingListItem(
                                        item = item,
                                        modifier = Modifier
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun RulingListItem(
    item : RulingsResponseData,
    modifier : Modifier
) {

    Text(
        text = "\u2022 ${item.comment.toString()}",
        fontSize = 18.sp,
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
fun LegalitiesColumn(
    legalities : List<String>,
    modifier : Modifier
) {

    legalities.forEach {
        Text(
            text = it,
            modifier = modifier
        )
    }
}

@Composable
fun NormalLayoutCard(
    modifier : Modifier,
    model : String?
) {

    SubcomposeAsyncImage(
        modifier = modifier
            .widthIn()
            .padding(
                top = 8.dp,
                start = 4.dp,
                end = 4.dp,
                bottom = 8.dp
            ),
        model = model,
        contentDescription = null
    )
}

@Composable
fun TransformLayoutCard(
    modifier : Modifier,
    model : String?,
    onClick : (String) -> Unit
) {
    SubcomposeAsyncImage(
        modifier = modifier
            .widthIn()
            .padding(
                top = 8.dp,
                start = 4.dp,
                end = 4.dp,
                bottom = 8.dp
            )
            .clickable { onClick(model.toString()) },
        model = model,
        contentDescription = null
    )
}