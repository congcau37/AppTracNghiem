package demo.utt37.congcau.apptracnghiem.service;

import java.util.ArrayList;
import java.util.Map;

import demo.utt37.congcau.apptracnghiem.response.ResponseHistory;
import demo.utt37.congcau.apptracnghiem.response.ResponseMessage;
import demo.utt37.congcau.apptracnghiem.response.ResponseScore;
import demo.utt37.congcau.apptracnghiem.response.ResponseStudent;
import demo.utt37.congcau.apptracnghiem.response.ResponseTeacher;
import demo.utt37.congcau.apptracnghiem.response.ResponseTest;
import demo.utt37.congcau.apptracnghiem.response.ResponseUser;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface SOService {

        //get
        @GET("getExam.php?getExam&format=json")
        Call<ArrayList<ResponseTest>> getExam(@QueryMap Map<String, Object> option);

        @GET("getStudent.php?getStudent&format=json")
        Call<ArrayList<ResponseStudent>> getStudent(@QueryMap Map<String, Object> option);

        @GET("getTeacher.php?getTeacher&format=json")
        Call<ArrayList<ResponseTeacher>> getTeacher(@QueryMap Map<String, Object> option);

        @GET("getHistory.php?getHistory&format=json")
        Call<ArrayList<ResponseHistory>> getHistory(@QueryMap Map<String, Object> option);

        @GET("getQuestion.php?getQuestion&format=json")
        Call<ArrayList<ResponseTest>> getQuestion(@QueryMap Map<String, Object> option);

        @GET("getScore.php?getScore&format=json")
        Call<ArrayList<ResponseScore>> getScore(@QueryMap Map<String, Object> option);

        @GET("getUser.php?getUser&format=json")
        Call<ArrayList<ResponseUser>> getUser(@QueryMap Map<String, Object> option);

        //post
        @FormUrlEncoded
        @POST("login.php")
        Call<ResponseMessage> login(@FieldMap Map<String, Object> params);

        @FormUrlEncoded
        @POST("saveTest.php")
        Call<ResponseMessage> saveTest(@FieldMap Map<String, Object> params);

        @FormUrlEncoded
        @POST("createTest.php")
        Call<ResponseMessage> createTest(@FieldMap Map<String, Object> params);

        @FormUrlEncoded
        @POST("createUser.php")
        Call<ResponseMessage> createUser(@FieldMap Map<String, Object> params);

        @FormUrlEncoded
        @POST("updateUser.php")
        Call<ResponseMessage> updateUser(@FieldMap Map<String, Object> params);

        @FormUrlEncoded
        @POST("updateTest.php")
        Call<ResponseMessage> updateTest(@FieldMap Map<String, Object> params);

        @FormUrlEncoded
        @POST("savePoint.php")
        Call<ResponseMessage> savePoint(@FieldMap Map<String, Object> params);
}
