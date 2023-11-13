package com.loc.newsapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loc.newsapp.R
import com.loc.newsapp.presentation.dimens
import com.loc.newsapp.ui.theme.NewsAppTheme


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    modifier : Modifier = Modifier,
    queryText : String,
    readOnly : Boolean,
    onClick : (()->Unit)?=null,
    onValueChange : (String) -> Unit,
    onSearch :() -> Unit,
    clearQueryTextCallback : ()->Unit

    ){
//    val interactionSource = remember{
//        MutableInteractionSource()
//    }
//
//    val isClicked = interactionSource.collectIsPressedAsState().value
//    LaunchedEffect(key1 = isClicked){
//        if(isClicked){
//            onClick?.invoke()
//        }
//    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Box {
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(dimens.VerySmallPadding1)
                .border(1.dp, Color.Black, shape = MaterialTheme.shapes.extraLarge),
            value = queryText,
            onValueChange = onValueChange,
            readOnly = false,
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(dimens.IconSize),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = colorResource(id = R.color.body)
                )
            },
            placeholder = {
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.placeholder),
                )
            },
            shape = MaterialTheme.shapes.extraLarge,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.input_background),
                textColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                cursorColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if(queryText.trim().isNotEmpty()){
                        onSearch()
                    }
                    clearQueryTextCallback()
                    // Close the keyboard
                    keyboardController?.hide()
                    // Remove the focus
                    focusManager.clearFocus()
                    //reset the queryText

                }
            ),
            textStyle = MaterialTheme.typography.bodySmall,
//            interactionSource = interactionSource
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchBarPreview() {
    NewsAppTheme {
        SearchBar(queryText = "", onValueChange = {}, readOnly = false, onSearch = {} ) {

        }
    }
}
