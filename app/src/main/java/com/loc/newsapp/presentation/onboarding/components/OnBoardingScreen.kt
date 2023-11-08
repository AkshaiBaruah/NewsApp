package com.loc.newsapp.presentation.onboarding.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.loc.newsapp.presentation.common.NewsButton
import com.loc.newsapp.presentation.dimens.MediumPadding1
import com.loc.newsapp.presentation.dimens.VerySmallPadding1
import com.loc.newsapp.presentation.onboarding.OnBoardingEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    event : (OnBoardingEvent) -> Unit             //this is a callback type(lambda se implement hua)
){
    val pagerState = rememberPagerState(initialPage = 0){
        pages.size
    }
    val nextBtnState = remember{
        derivedStateOf {
            when(pagerState.currentPage){
                2-> "Get Started"
                else ->"Next"
            }
        }
    }
    Column {
        HorizontalPager(state = pagerState) {
            OnBoardingPage(modifier = Modifier, page = pages[it])
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = MediumPadding1)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier.width(52.dp),
                pageSize = pages.size, currentPage = pagerState.currentPage
            )
            Row(modifier = Modifier.padding(MediumPadding1),
                verticalAlignment = Alignment.CenterVertically

            ) {
                val scope = rememberCoroutineScope()
                //back button
                if(pagerState.currentPage != 0){
                    NewsButton(text = "Back") {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                    Spacer(modifier = Modifier.size(VerySmallPadding1))
                }

                //next button
                NewsButton(
                    text = nextBtnState.value,
                ) { //this is the onClick lambda(used to implement a callback) that we are passing the NewsButton composable
                     scope.launch {
                         if(pagerState.currentPage == pages.lastIndex){
                             event(OnBoardingEvent.SaveAppEntry)
                         }
                         else{
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                         }
                     }

                }
            }
        }
    }
}