package com.loc.newsapp.presentation.onboarding.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.loc.newsapp.R
import com.loc.newsapp.presentation.dimens.SmallPadding2

@Composable
fun OnBoardingPage(modifier : Modifier, page : Page,){
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.68f),
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(SmallPadding2))
        Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
            .fillMaxHeight(0.6f)
        ) {
            Column {
                Text(
                    modifier = Modifier
                        .padding(SmallPadding2)
                        .fillMaxWidth(),
                    text = page.title,
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    ),
                    color = colorResource(id = R.color.display_small),

                    )
                Text(
                    modifier = Modifier.padding(SmallPadding2),
                    text = page.description,
                    color = colorResource(id = R.color.text_medium),
                    style = MaterialTheme.typography.bodyMedium
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES , showBackground = true)
@Composable
fun OnboardingPagePreview(){
    OnBoardingPage(modifier = Modifier, page = pages[0])
}