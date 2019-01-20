package com.practice.response.builder;

import com.practice.model.AbstractModel;
import com.practice.response.AbstractResponse;

public abstract class ClientAbstractResponseBuilder<C extends AbstractResponse, M extends AbstractModel> {

	protected C clientResponse;
	protected M model;

	public ClientAbstractResponseBuilder(C clientResponse, M model) {
		this.clientResponse = clientResponse;
		this.model = model;
		construct();
	}

	/** 客户端Response类的生成方法 */
	public C build() {
		return clientResponse;
	}

	protected abstract void construct();

}
