package com.loc.newsapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.Source
import com.loc.newsapp.presentation.dimens
import com.loc.newsapp.presentation.dimens.ArticleSize
import com.loc.newsapp.presentation.dimens.VerySmallPadding1


@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick : ((Article) -> Unit) ?= null
){
    val context = LocalContext.current
    Row(
        modifier = modifier.clickable {
            if (onClick != null) {
                onClick(article)
            }
        }
    ) {
        AsyncImage(
            modifier = Modifier
                .size(ArticleSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround ,
            modifier = Modifier
                .padding(horizontal = dimens.VerySmallPadding1)
                .height(ArticleSize)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.text_title),
                )
                Spacer(modifier = Modifier.width(VerySmallPadding1))
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    modifier = Modifier.size(dimens.SmallIconSize),
                    tint = colorResource(id = R.color.body),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(VerySmallPadding1))
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = colorResource(id = R.color.text_title),
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Preview(showBackground = true,  uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview(){
    ArticleCard(article = Article(
        author = "",
        content = "",
        description = "",
        publishedAt = "2hrs",
        source = Source(id = "" , name = "BBC"),
        title = "this is a title",
        url = "",
        urlToImage = ""
    )) {
        //this is the onclick
    }
}