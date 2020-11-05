package com.example.aidlserverfunction;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.example.aidlserver.IAdd;
import com.example.aidlserver.Person;

import java.util.List;

public class AdditionService extends Service {


	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	private final IAdd.Stub mBinder = new IAdd.Stub() {
		@Override
		public int addNumbers(int num1, int num2) throws RemoteException {
			return num1 + num2;
		}

		@Override
		public List<String> getStringList() throws RemoteException {
			return null;
		}

		@Override
		public List<Person> getPersonList() throws RemoteException {
			return null;
		}


		@Override
		public void placeCall(String number) throws RemoteException {

		}


	};
}