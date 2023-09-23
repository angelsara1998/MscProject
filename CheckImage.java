package com.example.classifierapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import org.tensorflow.lite.Interpreter;
import java.io.InputStream;
import android.graphics.Bitmap;
import android.widget.TextView;
import android.text.method.LinkMovementMethod;


import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;
import org.tensorflow.lite.support.common.TensorProcessor;
import org.tensorflow.lite.support.common.ops.NormalizeOp;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
import java.io.IOException;
import java.nio.ByteBuffer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import okhttp3.MultipartBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Request;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.OkHttpClient;
import java.io.IOException;
import android.widget.TextView;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.core.text.HtmlCompat;




import androidx.appcompat.app.AppCompatActivity;


// MainActivity.java
public class CheckImage extends AppCompatActivity {
    private Button donothingbutton;
    private TextView textView2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_image);

        donothingbutton = findViewById(R.id.donothingbutton);


        textView2 = findViewById(R.id.textView2);


        donothingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String storedimgpath = getIntent().getStringExtra("filePathExtra");

                Bitmap imageBitmap = BitmapFactory.decodeFile(storedimgpath);


                // Convert imageBitmap to bytes
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] imageBytes = stream.toByteArray();


                String apiUrl = "https://f8a2-34-74-8-209.ngrok.io/predict";
                MediaType mediaType = MediaType.parse("image/png");
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("image", "image.png",
                                RequestBody.create(imageBytes, mediaType))
                        .build();


                Request request = new Request.Builder()
                        .url(apiUrl)
                        .post(requestBody)
                        .build();

                // Handle API response
                OkHttpClient client = new OkHttpClient();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try{
                            String responseBody = response.body().string();
                            JSONObject jsonResponse = new JSONObject(responseBody);

                            // Extract the predicted class
                            String predictedClass = jsonResponse.getString("predicted_class");


                            JSONArray extractedResultsArray = jsonResponse.getJSONArray("extracted_results");

                            StringBuilder extractedInfo = new StringBuilder();
                            for (int i = 0; i < extractedResultsArray.length(); i++) {
                                JSONObject extractedResult = extractedResultsArray.getJSONObject(i);
                                String link = extractedResult.getString("link");
                                String title = extractedResult.getString("title");
                                String price = extractedResult.getString("price");

                                //extractedInfo.append("Thumbnail: ").append(thumbnailUrl).append("\n");
                                //extractedInfo.append("Title: ").append(title).append("\n\n");
                                //extractedInfo.append("<b>Link:</b> <a href='").append(link).append("\n");
                                extractedInfo.append("<b>Title:</b> ").append(title).append("<br>");
                                extractedInfo.append("<b>Price:</b> ").append(price).append("<br>");
                                extractedInfo.append("<a href='").append(link).append("'>").append("Click here</a><br><br>");


                            }



                            //final String resultText = "Predicted Class: " + predictedClass + "\n\n" +
                                   // "Extracted Results:\n" + extractedInfo.toString();

                            runOnUiThread(() -> {
                                //TextView textResult = findViewById(R.id.textResult);
                                donothingbutton.setVisibility(View.GONE);

                                textView2.setText(HtmlCompat.fromHtml(extractedInfo.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY));

                                textView2.setMovementMethod(LinkMovementMethod.getInstance());
                            });

                        }catch(JSONException e){
                            e.printStackTrace();
                        }

                        //String responseBody = response.body().string();


                    }
                });
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();

        donothingbutton.setVisibility(View.VISIBLE);
    }
}




