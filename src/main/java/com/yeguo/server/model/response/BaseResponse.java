package com.yeguo.server.model.response;

public class BaseResponse{
	
	private String ret;//返回结果  1失败 0 成功
	private String errcode;//返回结果失败的错误码
	private String errinfo;//返回结果失败的错误信息
	
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	
	public String getErrinfo() {
		return errinfo;
	}
	public void setErrinfo(String errinfo) {
		this.errinfo = errinfo;
	}
	
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	
	public BaseResponse() {
		// TODO Auto-generated constructor stub
	}
	public BaseResponse(String ret, String errinfo) {
		super();
		this.ret = ret;
		this.errinfo = errinfo;
	}
}
