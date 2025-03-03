package com.example.blood;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface BloodBankApiService {
    @GET("bloodbanks")
    Call<List<BloodBank>> getBloodBanks();
}
