package com.authapp.auth.presentation.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.authapp.ui.theme.BlueMain
import com.authapp.ui.theme.GreyText
import com.com.auth.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(navController: NavController){

    val onNextButtonClick = {
        navController.navigate("login")
    }

    WelcomeContent(
        onNextButtonClick = onNextButtonClick
    )

}


@Composable
fun WelcomeContent(onNextButtonClick : () -> Unit){


    val TOTAL_PAGE_COUNT = 3

    val pagerState = rememberPagerState(
        pageCount = { TOTAL_PAGE_COUNT }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 127.dp)


        ) { pageNum ->

            val painterId = when(pageNum ){
                0 -> R.drawable.wlcome_img_1
                1 -> R.drawable.welcome_img_2
                2 -> R.drawable.welcome_img_3
                else -> R.drawable.wlcome_img_1
            }

            val mainText = when(pageNum ){
                0 -> "Fastest Payment in the world"
                1 -> "The most Secoure Platfrom for Customer"
                2 -> "Paying for Everything is Easy and Convenient"
                else -> null
            }

            val subText = when(pageNum ){
                0 -> "Integrate multiple payment methoods to help you up the process quickly"
                1 -> "Built-in Fingerprint, face recognition and more, keeping you completely safe"
                2 -> "Built-in Fingerprint, face recognition and more, keeping you completely safe"
                else -> null

            }

            Column {


                WelcomeImage(mainText = mainText ?: "",
                    subText = subText ?: "",
                    imgId = painterId,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 31.dp))

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ){

                    repeat(TOTAL_PAGE_COUNT){ index ->


                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(
                                    if (pageNum == index) {
                                        BlueMain
                                    } else {
                                        Color.LightGray
                                    }
                                )
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                    }

                }



            }


        }


        Button(
            shape = RoundedCornerShape(50),
            onClick = onNextButtonClick,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
                .padding(horizontal = 31.dp),
        ) {
            Text(
                text = "Next",
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }




    }



}


@Composable
fun WelcomeImage(mainText : String,
                 subText : String,
                 imgId : Int,
                 modifier: Modifier = Modifier){
    
    
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,

    ){

        Image(
            painter = painterResource(id = imgId),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(250.dp))
        
        Spacer(modifier = Modifier.height(36.dp))
        
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = mainText,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(10.dp))
        
        Text(
            text = subText,
            color = GreyText,
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
        
        
    }
    
    
}