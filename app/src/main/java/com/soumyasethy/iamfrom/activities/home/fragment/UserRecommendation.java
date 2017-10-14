package com.soumyasethy.iamfrom.activities.home.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soumyasethy.iamfrom.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserRecommendation.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserRecommendation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserRecommendation extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String strResponse;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private View usrRecomendationView;
    private View mProgressView;

    public UserRecommendation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserRecommendation.
     */
    // TODO: Rename and change types and number of parameters
    public static UserRecommendation newInstance(String param1, String param2) {
        UserRecommendation fragment = new UserRecommendation();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //new GetUserRecommendation(getActivity(),"vishal89@gmail.com").execute((Void) null);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_recommendation, container, false);
        usrRecomendationView = rootView.findViewById(R.id.usrRecomendationView);
        mProgressView = rootView.findViewById(R.id.userRecomendationView);
        return rootView;

        // Inflate the layout for this fragment
        //eturn inflater.inflate(R.layout.user_recommendation, container, false);
        //new GetUserRecommendation(getActivity(),"vishal89@gmail.com").execute((Void) null);


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        showProgress(true);
        new GetUserRecommendation(getContext(), "vishal89@gmail.com").execute((Void) null);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            usrRecomendationView.setVisibility(show ? View.GONE : View.VISIBLE);
            usrRecomendationView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    usrRecomendationView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            usrRecomendationView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class GetUserRecommendation extends AsyncTask<Void, Void, Boolean> {

        ProgressDialog pDialog;
        Context c;
        String email, status, totalUserRecommendationCount;

        GetUserRecommendation(Context context, String email) {
            this.c = context;
            this.email = email;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
           /* pDialog = new ProgressDialog(c);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
*/
        }


        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            try {
                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, "{\n\n}");
                Request request = new Request.Builder()
                        .url("85.25.196.222:6013/user/v1/recommendation")
                        .post(body)
                        .addHeader("timezone", "Asia/Calcutta")
                        .addHeader("username", email)
                        .addHeader("content-type", "application/json")
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "4125a1b1-e203-eded-f4d4-4308e6ed93ba")
                        .build();


                Response response = client.newCall(request).execute();
                String strResponse = response.body().string().toString();
                JSONObject jsonObject = new JSONObject(strResponse);
                status = jsonObject.getString("status");
                totalUserRecommendationCount = jsonObject.getString("totalUserRecommendationCount");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            // Dismiss the progress dialog
            showProgress(false);
           /* if (pDialog.isShowing()) {
                pDialog.dismiss();*/

            //}

        }
    }
}
