package com.gmail.pentominto.us.mtgcardrulings.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.Legalities
import com.gmail.pentominto.us.mtgcardrulings.data.model.rulingsresponse.RulingsResponseData
import com.gmail.pentominto.us.mtgcardrulings.ui.theme.BackgroundGray
import kotlin.reflect.full.memberProperties

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

            if (detailsState.value.isLoading) {
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
                        when (detailsState.value.infoForDisplay?.layoutType) {

                            "transform" -> {
                                TransformLayoutCard(
                                    modifier = Modifier.weight(
                                        3f,
                                        false
                                    ),
                                    model = if (detailsState.value.frontSide) {
                                        detailsState.value.infoForDisplay?.twoSidedCardLayoutFrontImage
                                    } else {
                                        detailsState.value.infoForDisplay?.twoSidedCardLayoutBackImage
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
                                    model = if (detailsState.value.frontSide) {
                                        detailsState.value.infoForDisplay?.twoSidedCardLayoutFrontImage
                                    } else {
                                        detailsState.value.infoForDisplay?.twoSidedCardLayoutBackImage
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
                                    model = detailsState.value.infoForDisplay?.singleSidedCardLayoutImage,
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
                            detailsState.value.infoForDisplay?.legalities?.let { legalities ->
                                LegalitiesColumn(
                                    legalities = legalities
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

                            if (detailsState.value.rulingsData.isEmpty()) {
                                Text(
                                    text = "No additional rulings exist for this card.",
                                    modifier = Modifier.fillMaxWidth()
                                )
                            } else {
                                detailsState.value.rulingsData.forEach { item ->
                                    RulingListItem(item = item)
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
fun RulingListItem(item : RulingsResponseData) {

    Text(
        text = "\u2022 ${item.comment.toString()}",
        fontSize = 18.sp
    )
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
fun LegalitiesColumn(legalities : Legalities) {

    for (format in Legalities::class.memberProperties) {
        if (format.get(legalities).toString().contains(
                "not_legal",
                true
            )
        ) {
            /**
             * Filtering out "not_legal", could also look for "legal" instead
             */
        } else {
            when (format.name) {
                "paupercommander" -> {

                    Text(text = "PC")
                }
                "historicbrawl"   -> {
                    Text(text = "HB")
                }
                else              -> {
                    Text(format.name.replaceFirstChar { it.uppercase() })
                }
            }
        }
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