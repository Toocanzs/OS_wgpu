package webgpu;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import webgpu.WebgpuLibrary.WGPUErrorCallback;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("webgpu") 
public class WGPUUncapturedErrorCallbackInfo extends StructObject {
	public WGPUUncapturedErrorCallbackInfo() {
		super();
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public Pointer<WGPUChainedStruct > nextInChain() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public WGPUUncapturedErrorCallbackInfo nextInChain(Pointer<WGPUChainedStruct > nextInChain) {
		this.io.setPointerField(this, 0, nextInChain);
		return this;
	}
	/// C type : WGPUErrorCallback
	@Field(1) 
	public Pointer<WGPUErrorCallback > callback() {
		return this.io.getPointerField(this, 1);
	}
	/// C type : WGPUErrorCallback
	@Field(1) 
	public WGPUUncapturedErrorCallbackInfo callback(Pointer<WGPUErrorCallback > callback) {
		this.io.setPointerField(this, 1, callback);
		return this;
	}
	/// C type : void*
	@Field(2) 
	public Pointer<? > userdata() {
		return this.io.getPointerField(this, 2);
	}
	/// C type : void*
	@Field(2) 
	public WGPUUncapturedErrorCallbackInfo userdata(Pointer<? > userdata) {
		this.io.setPointerField(this, 2, userdata);
		return this;
	}
	public WGPUUncapturedErrorCallbackInfo(Pointer pointer) {
		super(pointer);
	}
}