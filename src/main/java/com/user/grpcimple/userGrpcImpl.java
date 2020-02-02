package com.user.grpcimple;

import com.user.grpc.User.MultipleAPIResponse;
import com.user.grpc.User.MultipleLoginRequests;
import com.user.grpc.userGrpc.userImplBase;

import io.grpc.stub.StreamObserver;

public class userGrpcImpl extends userImplBase {

	public StreamObserver<MultipleLoginRequests> login(
			StreamObserver<MultipleAPIResponse> responseObserver)
	{
		StreamObserver<MultipleLoginRequests> requestObserver = new StreamObserver<MultipleLoginRequests>() {
			public void onNext(MultipleLoginRequests value) {
				String result = "receive " + value.getRequests().getUsername() +value.getRequests().getPassword();
				MultipleAPIResponse response = MultipleAPIResponse.newBuilder()
						.setResponse(result)
						.build();
				responseObserver.onNext(response);
			}
			
			public void onError(Throwable t) {
				
			}
			
			public void onCompleted() {
				responseObserver.onCompleted();
			}
		};
		
		return requestObserver;
	}
}
