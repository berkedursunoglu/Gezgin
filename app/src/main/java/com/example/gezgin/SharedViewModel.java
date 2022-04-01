package com.example.gezgin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Places>> arrayListMutableLiveData;

    public void setArrayListMutableLiveData(ArrayList<Places> input){
        arrayListMutableLiveData = new MutableLiveData<>();
        arrayListMutableLiveData.setValue(input);
    }

    public LiveData<ArrayList<Places>> getArrayListMutableLiveData(){
        return arrayListMutableLiveData;
    }
}
