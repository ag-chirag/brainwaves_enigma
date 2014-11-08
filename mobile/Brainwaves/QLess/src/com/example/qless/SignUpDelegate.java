package com.example.qless;

public interface SignUpDelegate {

	public   	void  	failSignUp(String errorDesc, int type);
	public 		void 	notifySginUp(SignUpResponseJson signUpResponseJsons);
}
