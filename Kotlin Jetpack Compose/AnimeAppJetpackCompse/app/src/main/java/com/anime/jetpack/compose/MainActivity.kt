package com.anime.jetpack.compose


import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anime.app.viewmodel.AnimeQuoteViewModel
import com.anime.jetpack.compose.constants.Constants
import com.anime.jetpack.compose.model.AnimeQuote
import com.anime.jetpack.compose.repository.AnimeQuoteRepository
import com.anime.jetpack.compose.ui.theme.AnimeAppJetpackCompseTheme
import com.anime.jetpack.compose.viewmodel.ViewModelFactory
import com.skydoves.landscapist.glide.GlideImage

class MainActivity : ComponentActivity() {
    //private lateinit var viewModel: AnimeQuoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //val repository = AnimeQuoteRepository()
        //val viewModelFactory = ViewModelFactory(repository)
        //viewModel = ViewModelProvider(this, viewModelFactory).get(AnimeQuoteViewModel::class.java)
        //viewModel.getTenAnimeQuotes()

//        viewModel.animeQuoteList.observe(this, Observer { result ->
//            result.body()?.let {animeQuotes ->
//
//                animeQuoteAdapter.submitList(animeQuotes)
//            }
//
//        })
//        binding.fetchResourcesButton.setOnClickListener {
//            viewModel.getTenAnimeQuotes()
//        }
        val buttonName  = "Fetch Anime Quotes"
        setContent {
            AnimeAppJetpackCompseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val owner = LocalViewModelStoreOwner.current
                    owner?.let {
                        val viewModel: AnimeQuoteViewModel =
                            viewModel(it, "AnimeQuoteViewModel", ViewModelFactory(AnimeQuoteRepository()))
                        //viewModel.getTenAnimeQuotes()
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            //Greeting("Android")
                            AnimeQuoteLazyColumn(LocalContext.current, viewModel)
//                            AnimeQuoteList(
//                                context = LocalContext.current,
//                                animeQuote = AnimeQuote(
//                                    "Naruto",
//                                    "Naruto",
//                                    "Dattebayo"
//                                )
//                            )
                            FetchResourceButton(viewModel = viewModel, name = buttonName)
                        }
                    }
                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}

@Composable
fun FetchResourceButton(viewModel: AnimeQuoteViewModel, name: String) {
    Button(onClick = {
        viewModel.getTenAnimeQuotes()
    }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff6DB0FF))
    ) {
        Text(name)
    }
}

@Composable
fun AnimeQuoteList(context: Context, animeQuote: AnimeQuote) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background Img",
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(84.dp),
            contentScale = ContentScale.Crop,

        )
        Row {
            AnimeQuoteImage(Constants.ANIME_IMG_URL)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                    .clickable(
                        onClick = {
                            Toast
                                .makeText(
                                    context,
                                    animeQuote.character + "-" + animeQuote.quote,
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                    )
            ) {
                Text(
                    text = animeQuote.anime + "-" + animeQuote.character,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color(0xFFFFAA00)
                )
                Text(text = animeQuote.quote, maxLines = 2, overflow = TextOverflow.Ellipsis, color = Color(0xFFFFAA00))
            }
        }

    }
}

@Composable
private fun AnimeQuoteImage(url: String) {
    /*AsyncImage(
        model = url,
        contentDescription = "AnimeImage",
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )*/
    GlideImage(
        imageModel = url,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp))),
        contentDescription = "Anime Img",

        )
}


//https://proandroiddev.com/how-to-make-a-recyclerview-in-jetpack-compose-bf4751abee80
//https://github.com/aqua30/FormValidation/blob/master/app/src/main/java/com/aqua30/formvalidation/screens/BankForm.kt
@Composable
fun AnimeQuoteLazyColumn(
    context: Context,
    animeQuoteViewModel: AnimeQuoteViewModel
) {
    //val animeQuotes = animeQuoteViewModel.animeQuoteList.value?.body()
    //https://developer.android.com/reference/kotlin/androidx/compose/runtime/livedata/package-summary
    //The inner observer will automatically be removed when this composable disposes or the current LifecycleOwner moves to the Lifecycle.State.DESTROYED state.
    val animeQuotes by animeQuoteViewModel.animeQuoteList.observeAsState()
    //Log.d("animeQuoteSize", animeQuotes.toString())
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight(0.7f)

    ) {
        //Log.d("animeQuotes", animeQuotes?.body()?.size.toString())
        animeQuotes?.body()?.let {
            items(it) { animeQuote ->
                AnimeQuoteList(context = context, animeQuote = animeQuote)

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnimeAppJetpackCompseTheme {
        //Greeting("Android")
        AnimeQuoteList(
            context = LocalContext.current,
            animeQuote = AnimeQuote("Naruto", "Naruto", "Dattebayo")
        )
    }
}

/*
* https://stackoverflow.com/questions/63707250/how-to-center-a-button-in-jetpack-compose
* https://stackoverflow.com/questions/66414177/putting-something-below-a-lazycolumn-in-jetpack-compose
* https://stackoverflow.com/questions/58743541/how-to-get-context-in-jetpack-compose
* https://stackoverflow.com/questions/70877225/jetpack-compose-no-effect-for-clickable-item
* https://developer.android.com/jetpack/compose/libraries  -> viewModel
* https://stackoverflow.com/questions/66560404/jetpack-compose-unresolved-reference-observeasstate
* https://www.answertopia.com/jetpack-compose/a-jetpack-compose-room-database-and-repository-tutorial/
* https://www.answertopia.com/jetpack-compose/working-with-viewmodels-in-jetpack-compose/
* https://stackoverflow.com/questions/71817213/jetpack-compose-lazycolumn-how-to-update-values-of-each-item-seperately
* https://developer.android.com/jetpack/compose/lists
* https://developer.android.com/topic/libraries/architecture/viewmodel#compose
* https://github.com/aqua30/FormValidation/blob/master/app/src/main/java/com/aqua30/formvalidation/screens/BankForm.kt
* https://github.com/aqua30/FormValidation/blob/master/app/src/main/java/com/aqua30/formvalidation/screens/BankFormActivity.kt
* https://github.com/aqua30/FormValidation/blob/master/app/src/main/java/com/aqua30/formvalidation/screens/BankFormViewModel.kt
* https://proandroiddev.com/cleaner-way-to-interact-between-composable-and-viewmodel-in-jetpack-compose-14c8b3a74bbe
* https://developer.android.com/jetpack/compose/state
* https://stackoverflow.com/questions/68779935/how-do-you-use-compose-lazycolumn-with-coroutines-room-database
* https://github.com/skydoves/landscapist
* https://proandroiddev.com/loading-images-for-jetpack-compose-using-glide-coil-and-fresco-1211261a296e
* https://stackoverflow.com/questions/67699823/module-was-compiled-with-an-incompatible-version-of-kotlin-the-binary-version-o
* https://developer.android.com/jetpack/compose/documentation
* https://coil-kt.github.io/coil/compose/
* https://www.geeksforgeeks.org/how-to-load-image-from-url-in-android-using-jetpack-compose/
* https://proandroiddev.com/async-image-loading-the-jetpack-compose-way-2686d1ac5a53
* https://stackoverflow.com/questions/58594262/how-do-i-load-url-into-image-into-drawimage-in-compose-ui-android-jetpack
* https://stackoverflow.com/questions/66957702/how-to-load-image-from-url-in-jetpack-compose
* https://www.jetpackcompose.net/buttons-in-jetpack-compose
* https://proandroiddev.com/how-to-make-a-recyclerview-in-jetpack-compose-bf4751abee80
* https://www.waseefakhtar.com/android/recyclerview-in-jetpack-compose/
* https://www.raywenderlich.com/15361077-jetpack-compose-tutorial-for-android-getting-started
* https://proandroiddev.com/ui-widgets-from-scratch-in-jetpack-compose-b16a74ca95e5
* https://developer.android.com/courses/jetpack-compose/course
* https://developer.android.com/jetpack/compose/tutorial
* https://www.tutorialkart.com/android-jetpack-compose/card-background-image/
* https://stackoverflow.com/questions/67151952/android-jetpack-compose-image-cant-scale-to-boxs-width-and-height
* */