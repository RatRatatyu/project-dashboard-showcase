package com.example.projectdashboardshowcase.presentation.mainScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectdashboardshowcase.R
import com.example.projectdashboardshowcase.core.domain.model.User
import com.example.projectdashboardshowcase.presentation.mainScreen.UserState
import com.example.projectdashboardshowcase.ui.theme.ProjectDashboardShowcaseTheme


@Composable
fun DashboardTopAppBar(
    modifier: Modifier = Modifier,
    userState: UserState
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.app_logo),
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(40.dp)
        )

        Text(
            text = stringResource(R.string.main_page),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(start = 12.dp)
        )

        Spacer(modifier = Modifier.weight(1f))
        
        UserAvatar(userState = userState)

    }
}

@Composable
fun UserAvatar(
    modifier: Modifier = Modifier,
    userState: UserState
){
    val avatarSize = 40.dp
    val defaultAvatarColor = MaterialTheme.colorScheme.primaryContainer


    Box(
        modifier = modifier
            .size(avatarSize)
            .clip(CircleShape)
            .background(defaultAvatarColor)
    ){
        when (userState){
            is UserState.Loading ->
                Spacer(Modifier)

            is UserState.Success ->{
                val user = userState.user

                if(user.avatarUrl != null){
                    Image(
                        painter = painterResource(user.avatarUrl),
                        contentDescription = stringResource(R.string.user_avatar),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                            .clip(CircleShape)

                    )
                }else{
                    //TODO(create default user avatar)
                }
            }
            is UserState.Error ->
                Icon(
                    painter = painterResource(R.drawable.ic_user),
                    contentDescription = stringResource(R.string.error_loading_user_avatar),
                    modifier = Modifier.padding(10.dp)
                )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardTopAppBarPreview() {
    ProjectDashboardShowcaseTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column {
                Text("Loading", modifier = Modifier.padding(8.dp), style = MaterialTheme.typography.labelSmall)
                DashboardTopAppBar(
                    modifier = Modifier.height(64.dp).fillMaxWidth(),
                    userState = UserState.Loading
                )
                HorizontalDivider()
                
                Text("Success", modifier = Modifier.padding(8.dp), style = MaterialTheme.typography.labelSmall)
                DashboardTopAppBar(
                    modifier = Modifier.height(64.dp).fillMaxWidth(),
                    userState = UserState.Success(User(1, "Анна", R.drawable.user1))
                )
                HorizontalDivider()

                Text("Error", modifier = Modifier.padding(8.dp), style = MaterialTheme.typography.labelSmall)
                DashboardTopAppBar(
                    modifier = Modifier.height(64.dp).fillMaxWidth(),
                    userState = UserState.Error("Error")
                )
            }
        }
    }
}
