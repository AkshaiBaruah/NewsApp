package com.loc.newsapp.presentation.home

import android.graphics.Color
import android.provider.CalendarContract.Colors
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.common.ArticleList
import com.loc.newsapp.presentation.common.SearchBar
import com.loc.newsapp.presentation.dimens
import com.loc.newsapp.presentation.navgraph.Route

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigate : (String) ->Unit,
    onClickDetails :(Article) -> Unit,
    onClickBookmark : ()->Unit,
){
    val titles by remember {
        derivedStateOf {
            if(articles.itemCount > 10){
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0 , endInclusive = 9))
                    .joinToString(separator = " || "){ it.title}
            }
            else{
                ""
            }
        }
    }
    var queryText by remember {
        mutableStateOf("")
    }
    fun clearQueryTextCallback(){
        queryText = ""
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween , modifier = Modifier.fillMaxWidth()) {

            Row(modifier = Modifier.padding(dimens.SmallPadding1)) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimens.LogoSize)
                        .statusBarsPadding()
                )
                Text(
                    text = "News",
                    modifier = Modifier
                        .padding(start = dimens.SmallPadding1)
                        .align(Alignment.CenterVertically),
                    style = TextStyle(
                        fontSize = dimens.TitleSize,
                        fontFamily = FontFamily.SansSerif
                    )
                )

            }
            Icon(
                modifier = Modifier.height (50.dp).padding(end = dimens.MediumPadding1).fillMaxHeight().align(Alignment.CenterVertically)
                    .clickable {
                        onClickBookmark()
                    },
                painter = painterResource(id = R.drawable.ic_bookmark),
                contentDescription = null
            )
        }

        //Spacer(modifier = Modifier.padding(dimens.TinyPadding))

        SearchBar(
            modifier = Modifier.padding(start = dimens.SmallPadding2 , end = dimens.SmallPadding2),
            queryText = queryText,
            readOnly = true,
            onValueChange ={
                queryText = it
            },
            onClick = {},
            onSearch = {navigate(Route.SearchScreen.route + "/$queryText")},
            clearQueryTextCallback = {clearQueryTextCallback()}
        )
        //Spacer(modifier = Modifier.height(dimens.MediumPadding1))
        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimens.SmallPadding2)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )
        //Spacer(modifier = Modifier.height(dimens.MediumPadding1))

        ArticleList(
            modifier = Modifier.padding(horizontal = dimens.SmallPadding1),
            articles = articles,
            onClick = onClickDetails         //have to implement the details screen
        )
    }
}


