package com.example.guessnumberfragments2.fragments;

import static com.example.guessnumberfragments2.R.navigation.nav_graph;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.guessnumberfragments2.GuessNum;
import com.example.guessnumberfragments2.R;
import com.example.guessnumberfragments2.databinding.MainFragmentBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainFragment extends Fragment {
    public MainFragment(){
        super(R.layout.main_fragment);
    }
    private MainFragmentBinding binding;

    int comp_num = 0;
    TextView attempts;
    TextView hint;
    TextView numUser;
    Button guessBtn;
    Button modeBtn;
    int checkedItem = 0;
    int previousChecked = 0;
    private static final int CHANGE_USER_NAME = 1;
    private static final int NOTIFICATION_ID = 101;
    private static final String MESSAGE_TO_SHARE = "MESSAGE TO SHARE";
    private static final String CHANNEL_ID = "channelID";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = MainFragmentBinding.bind(view);
        if (savedInstanceState != null) {
            binding.numUserText.setText(savedInstanceState.getString("numUserText"));
            binding.nameUser.setText(savedInstanceState.getString("userName"));
        }
        //    CreateNotificationChannel();
        registerForContextMenu(binding.numUserText);
        attempts = binding.attemptsLeftTxt;
        comp_num = GuessNum.rndCompNum();
        //modeBtn = binding.modeBtn;
        guessBtn = binding.guessBtn;
        hint = binding.hintShowTxt;
        numUser = binding.numUserText;
        View.OnClickListener clickListenerEnter = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickBtnEnter();
            }
        };
        /*binding.notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle(getString(R.string.notification_title))
                        .setContentText(getString(R.string.notification_text))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }
        });*/
        binding.btnShare.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"QueryPermissionsNeeded", "IntentReset"})
            @Override
            public void onClick(View v) {
                String message;
                String[] partsOfLine;
                if (guessBtn.getText().toString().equals(getString(R.string.right))) {
                    partsOfLine = getString(R.string.message_share_guessed).split(",");
                    message = partsOfLine[0] + (checkedItem + 2) + partsOfLine[1] +
                            (totalAttempts - Integer.parseInt(binding.attemptsLeftTxt.getText().toString())) + partsOfLine[2];
                } else {
                    partsOfLine = getString(R.string.message_share_not_guessed).split(",");
                    message = partsOfLine[0] + (checkedItem + 2) + partsOfLine[1] + totalAttempts + partsOfLine[2];
                }
                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.addCategory(Intent.CATEGORY_DEFAULT);
                //intentShare.setPackage("com.miui.notes");
                intentShare.setType("text/plain");
                intentShare.putExtra(Intent.EXTRA_TEXT, message);
//                if (intentShare.resolveActivity(getPackageManager()) != null)
//                    startActivity(intentShare);//chooser);
//                else Log.i("SHARE", "Failed");
            }
        });
        binding.btnSaveNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentShare = new Intent(Intent.ACTION_SEND);// "com.miui.notes");
               intentShare.setPackage("com.miui.notes");
                intentShare.putExtra(Intent.EXTRA_TEXT,"Hi");
                intentShare.setType("text/plain");
                //if (intentShare.resolveActivity(getPackageManager()) != null)
                    startActivity(intentShare);//chooser);//EXTRA_TEXivity(intentShare);
                Calendar c = Calendar.getInstance();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat dateformat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                List<String> dataForSave = new ArrayList<String>();//Arrays.asList(dateformat.format(c.getTime()));
                dataForSave.add(dateformat.format(c.getTime()));
                dataForSave.add(String.valueOf(totalAttempts - Integer.parseInt(binding.attemptsLeftTxt.getText().toString())));
                dataForSave.add(String.valueOf(comp_num));
                Log.i("TIME", dataForSave.get(2));
                String datetime = dateformat.format(c.getTime());
            }
        });
        guessBtn.setOnClickListener(clickListenerEnter);
    }


//    private void CreateNotificationChannel() {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is not in the Support Library.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = getString(R.string.channel_name);
//            String description = "Meow";
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);
//            // Register the channel with the system; you can't change the importance
//            // or other notification behaviors after this.
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }
//    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_item_settings) {
            ShowDialog();
            return true;
        } else if (id == R.id.menu_item_about_developers) {
            NavController navController = Navigation.findNavController(getActivity(), R.id.fragmentContainerView);
            //@SuppressLint("ResourceType") NavController navController = Navigation.findNavController(getActivity().findViewById(R.navigation.nav_graph));
            navController.navigate(R.id.developersFragment);
            //NavigationUI.onNavDestinationSelected(item, navController);//для активити(скорее всего)
            return true;
        } else if (id == R.id.menu_item_change_user) {
            NavController navController = Navigation.findNavController(getActivity(), R.id.fragmentContainerView);
            navController.navigate(R.id.changePlayerFragment);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


//    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        Intent data = result.getData();
//                        binding.nameUser.setText(data.getStringExtra("USER_NAME"));
//                    }
//                }
//            });



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("numUserText", binding.numUserText.getText().toString());
        outState.putString("userName", (String) binding.nameUser.getText());
        super.onSaveInstanceState(outState);
    }

    public void ClickBtnEnter() {
        if (numUser.getText().toString().isEmpty()) {
            Toast t = Toast.makeText(getActivity(), R.string.empty_num, Toast.LENGTH_LONG);
            t.show();
        } else {
            if (guessBtn.getText().toString().equals(getString(R.string.right))) {
                Toast t = Toast.makeText(getActivity(), R.string.number_already_guessed, Toast.LENGTH_LONG);
                t.show();
            } else {
                int numHint = Integer.parseInt(attempts.getText().toString());
                if (numHint > 0) {
                    try {
                        int userNum = Integer.parseInt(numUser.getText().toString());
                        if (userNum == comp_num) {
                            guessBtn.setText(R.string.right);
                            Toast t = Toast.makeText(getActivity(), R.string.wishYou, Toast.LENGTH_LONG);
                            t.show();
                            binding.btnShare.setVisibility(View.VISIBLE);
                            binding.btnSaveNotes.setVisibility(View.VISIBLE);
                        } else if (userNum < comp_num) {
                            hint.setText(R.string.bigger);
                            attempts.setText(Integer.toString(numHint - 1));
                            numUser.setText("");
                        } else {

                            hint.setText(R.string.lower);
                            attempts.setText(Integer.toString(numHint - 1));
                            numUser.setText("");
                        }
                    } catch (NumberFormatException e) {
                        Toast t = Toast.makeText(getActivity(), R.string.incorrect, Toast.LENGTH_LONG);
                        t.show();
                        numUser.setText("");
                    }
                } else {
                    Toast t = Toast.makeText(getActivity(), R.string.no_attempts, Toast.LENGTH_LONG);
                    t.show();
                    binding.btnShare.setVisibility(View.VISIBLE);
                    binding.btnSaveNotes.setVisibility(View.VISIBLE);
                    numUser.setText("");
                }
            }
        }
    }

    public void ShowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_mode_title);
        builder.setSingleChoiceItems(R.array.mode_items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkedItem = which;
            }
        });
        builder.setPositiveButton(R.string.dialog_positive_btn_mode, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                AfterDialog();
            }
        });
        builder.setNegativeButton(R.string.dialog_negative_btn_txt, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkedItem = previousChecked;
                dialog.cancel();
                AfterDialog();
            }
        });
        AlertDialog dialogMode = builder.create();
        dialogMode.show();
    }

    int totalAttempts = 5;

    public void AfterDialog() {
        switch (checkedItem) {
            case 0:
                comp_num = GuessNum.rndCompNum();
                attempts.setText("5");
                totalAttempts = 5;
                hint.setText(R.string.hint_show_txt);
                break;
            case 1:
                comp_num = GuessNum.rndCompNum3();
                attempts.setText("7");
                totalAttempts = 7;
                hint.setText(R.string.hint_for_3);
                break;
            case 2:
                comp_num = GuessNum.rndCompNum4();
                attempts.setText("10");
                totalAttempts = 10;
                hint.setText(R.string.hint_for_4);
                break;
        }
        guessBtn.setText(R.string.guessBtn);
        binding.btnShare.setVisibility(View.INVISIBLE);
        binding.btnSaveNotes.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
