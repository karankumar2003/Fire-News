package com.example.firenews.components.newsrow

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.firenews.R
import com.example.firenews.models.Article
import com.example.firenews.models.Source


@Composable
fun NewsRow(
    modifier: Modifier = Modifier,
    newsArticle: Article,
    newsRowViewModel: NewsRowViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable {

                val intent = CustomTabsIntent
                    .Builder()
                    .build()
                intent.intent.`package` = "com.android.chrome"
                intent.launchUrl(context, Uri.parse(newsArticle.url))

            }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Top)
                        .weight(1f)
                ) {
                    Text(
                        newsArticle.title ?: "",
                        modifier = Modifier,
                        style = MaterialTheme.typography.titleMedium,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 5
                    )
                }


                Image(
                    rememberImagePainter(newsArticle.urlToImage),
                    contentDescription = "News Image",
                    modifier = Modifier
                        .height(90.dp)
                        .width(120.dp)
                        .align(Alignment.Top)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.FillBounds

                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                var expanded by remember {
                    mutableStateOf(false)
                }

                Text(
                    "${newsArticle.source?.name} • 1 Mar, 2023 ",
                    modifier = Modifier,
                    style = MaterialTheme.typography.bodySmall
                )
                IconButton(
                    onClick = { expanded = true }, modifier = Modifier
                        .size(22.dp)
                ) {
                    Icon(
                        Icons.Default.MoreVert, contentDescription = "Options",

                        )
                    DropdownMenu(expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        val localClipBoardManager = LocalClipboardManager.current
                        val context = LocalContext.current
                        DropdownMenuItem(text = {
                            Text("Share")
                        },
                            onClick = {
                                val intent = Intent()
                                intent.action = Intent.ACTION_SEND
                                intent.putExtra(Intent.EXTRA_TEXT, newsArticle.url)
                                intent.setType("text/plain")

                                if (intent.resolveActivity(context.packageManager) != null) {
                                    context.startActivity(intent)
                                }
                            },
                            leadingIcon = {
                                Icon(Icons.Default.Share, "Share")
                            }
                        )

                        DropdownMenuItem(text = {
                            Text("Copy Link")
                        },
                            onClick = {
                                localClipBoardManager.setText(
                                    AnnotatedString(
                                        newsArticle.url ?: "No link available"
                                    )
                                )
                                Toast.makeText(
                                    context,
                                    "Link copied to clipboard",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            leadingIcon = {
                                Icon(painterResource(id = R.drawable.link_icon), "Share")
                            }
                        )
                        DropdownMenuItem(text = {
                            Text("Save Article")
                        },
                            onClick = {
                                      newsRowViewModel.saveArticle(newsArticle, context)
                            },
                            leadingIcon = {
                                Icon(Icons.Default.FavoriteBorder, "Save")
                            }
                        )
                    }
                }


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsRowPreview() {
    NewsRow(
        newsArticle = Article(
            "",
            "",
            "",
            "",
            Source("", "The DeshBhakt"),
            "Local Pigeon Caught Red-Feathered, Denies ",
            "",
            ""
        )
    )
}
