package com.example.guessnumberfragments2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @SuppressLint("QueryPermissionsNeeded")
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.menu_item_settings) {
//            //ShowDialog();
//            return true;
//        } else if (id == R.id.menu_item_about_developers) {
////            Intent intent = new Intent(getActivity(), DevelopersFragment.class);
////            startActivity(intent);
//            NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
////            @SuppressLint("ResourceType") NavController navController = Navigation.findNavController(findViewById(R.navigation.nav_graph));
//            navController.navigate(R.id.developersFragment);
//            //NavigationUI.onNavDestinationSelected(item, navController);
//            return true;
//        } else if (id == R.id.menu_item_change_user) {
//            /*Intent intent = new Intent(this, ToChangePlayerActivity.class);
//            intent.putExtra("USER_NAME",binding.nameUser.getText());
//            startActivityForResult(intent,CHANGE_USER_NAME);*/
//            //mGetContent.launch("text/plain");
////            Intent intent = new Intent(getActivity(), ChangePlayerFragment.class);
////            intent.putExtra("NAME_USER",binding.nameUser.getText());
////            someActivityResultLauncher.launch(intent);
//            return true;
//        } else {
//            return super.onOptionsItemSelected(item);
//        }
//
//    }

}