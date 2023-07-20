package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsapp.ui.screens.article_screen.ArticleScreen
import com.example.newsapp.ui.screens.news_screen.NewsScreen
import com.example.newsapp.util.Constants.Companion.NEWS_SCREEN
import com.example.newsapp.viewmodels.NewsScreenViewModel

@Composable
fun NavGraphSetup(
    navController: NavHostController
) {
    val argKey = "web_url"
    NavHost(
        navController = navController,
        startDestination = NEWS_SCREEN
    ) {
        composable(route = NEWS_SCREEN) {
            val viewModel : NewsScreenViewModel = hiltViewModel()
            NewsScreen(
                state = viewModel.state,
                onEvent = viewModel::onEvent,
                onReadFullStoryButtonClicked = { url ->
                    navController.navigate("article_screen?$argKey=$url")
                }
            )
        }
        composable(
            route = "article_screen?$argKey={$argKey}",
            arguments = listOf(navArgument(name = argKey){
                type = NavType.StringType
            })
        ) { backStackEntry ->
            ArticleScreen(
                url = backStackEntry.arguments?.getString(argKey),
                onBackPressed = { navController.navigateUp() }
            )
        }
    }
}