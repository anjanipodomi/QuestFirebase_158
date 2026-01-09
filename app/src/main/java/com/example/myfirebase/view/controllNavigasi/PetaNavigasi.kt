package com.example.myfirebase.view.controllNavigasi


import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myfirebase.view.DetailSiswaScreen
import com.example.myfirebase.view.EditSiswaScreen
import com.example.myfirebase.view.EntrySiswaScreen
import com.example.myfirebase.view.HomeScreen
import com.example.myfirebase.view.route.DestinasiDetail
import com.example.myfirebase.view.route.DestinasiEdit
import com.example.myfirebase.view.route.DestinasiEntry
import com.example.myfirebase.view.route.DestinasiHome
import com.example.myfirebase.viewmodel.DetailViewModel
import com.example.myfirebase.viewmodel.PenyediaViewModel

@Composable
fun DataSiswaApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    HostNavigasi(navController = navController)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntry.route)
                },
                navigateToItemUpdate = {
                    navController.navigate("${DestinasiDetail.route}/${it}")
                }
            )
        }

        composable(DestinasiEntry.route) {
            EntrySiswaScreen(
                navigateBack = {
                    navController.navigate(DestinasiHome.route)
                }
            )
        }

        composable(
            DestinasiDetail.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetail.itemIdArg) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            key(backStackEntry.id) {

                val viewModel: DetailViewModel = viewModel(
                    viewModelStoreOwner = backStackEntry,
                    factory = PenyediaViewModel.Factory
                )

                DetailSiswaScreen(
                    navigateToEditItem = {
                        navController.navigate("${DestinasiEdit.route}/$it")
                    },
                    navigateBack = {
                        navController.navigate(DestinasiHome.route)
                    },
                    viewModel = viewModel
                )
            }
        }


        composable(
            DestinasiEdit.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiEdit.itemIdArg) {
                    type = NavType.StringType
                }
            )
        ) {
            EditSiswaScreen(
                navigateBack = {
                    navController.navigate(DestinasiHome.route)
                },
                onNavigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}