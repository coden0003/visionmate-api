package se.visionmate.api.v1.service;

import org.springframework.stereotype.Service;

import se.visionmate.api.v1.session.CallResponse;
import se.visionmate.api.v1.session.ErrorMessage;
import se.visionmate.api.v1.session.SuccessMessage;

@Service
public class MsgService {
	
	public CallResponse returnError(ErrorMessage msg) {
		return new CallResponse(msg);
	}
	
	public CallResponse returnSuccess(SuccessMessage msg) {
		return new CallResponse(msg);
	}
	 
}
