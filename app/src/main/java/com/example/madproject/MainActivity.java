package com.example.madproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
public class MainActivity extends AppCompatActivity {
  //  private CallbackManager callbackManager;
    public static final String TAG = "MainActivity";
    //a constant for detecting the login intent result
    private static final int RC_SIGN_IN = 234;
Button b;

    //creating a GoogleSignInClient object
    GoogleSignInClient mGoogleSignInClient;

    //And also a Firebase Auth object 
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //first we intialized the FirebaseAuth object
        mAuth = FirebaseAuth.getInstance();
      /*  // Initialize your instance of callbackManager//
        callbackManager = CallbackManager.Factory.create();

        // Register your callback//
        LoginManager.getInstance().registerCallback(callbackManager,

                // If the login attempt is successful, then call onSuccess and pass the LoginResult//
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // Print the user’s ID and the Auth Token to Android Studio’s Logcat Monitor//
                        Log.d(TAG, "User ID: " +
                                loginResult.getAccessToken().getUserId() + "\n" +
                                "Auth Token: " + loginResult.getAccessToken().getToken());
                        startActivity(new Intent(MainActivity.this,activity_profile.class));
                        Toast.makeText(getApplicationContext(),"loginn",Toast.LENGTH_SHORT).show();
                    }

                    // If the user cancels the login, then call onCancel//
                    @Override
                    public void onCancel() { Log.d(TAG,"CANCEL");
                    }

                    // If an error occurs, then call onError//
                    @Override
                    public void onError(FacebookException exception) {
                        Log.d(TAG,"ERROR");
                    }
                });*/
        b=(Button)findViewById(R.id.button2);
        final Intent intent1=new Intent(this,otplogin.class);
b.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {
startActivity(intent1);
    }
});
        //Then we need a GoogleSignInOptions object
        //And we need to build it as below

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //Then we will get the GoogleSignInClient object from GoogleSignIn class
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //Now we will attach a click listener to the sign_in_button
        //and inside onClick() method we are calling the signIn() method that will open
        //google sign in intent 
        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        //if the user is already signed in
        //we will close this activity
        //and take the user to profile activity 
        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, Main3Activity.class));
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if the requestCode is the Google Sign In code that we defined at starting
        if (requestCode == RC_SIGN_IN) {

            //Getting the GoogleSignIn Task 
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                //authenticating with firebase 
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        //Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        final Intent intent = new Intent(this, activity_profile.class);
        //getting the auth credential 
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        //Now using firebase we are signing in the user here 
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                           // Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "User Signed In", Toast.LENGTH_SHORT).show();


                        } else {
                            // If sign in fails, display a message to the user.
                         //   Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }


    //this method is called on click 
    private void signIn() {
        //getting the google signin intent
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();

        //starting the activity for result 
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

}