package webgpu;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import webgpu.WebgpuLibrary.WGPUCallbackMode;
import webgpu.WebgpuLibrary.WGPUCreateComputePipelineAsyncCallback;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("webgpu") 
public class WGPUCreateComputePipelineAsyncCallbackInfo extends StructObject {
	public WGPUCreateComputePipelineAsyncCallbackInfo() {
		super();
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public Pointer<WGPUChainedStruct > nextInChain() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public WGPUCreateComputePipelineAsyncCallbackInfo nextInChain(Pointer<WGPUChainedStruct > nextInChain) {
		this.io.setPointerField(this, 0, nextInChain);
		return this;
	}
	/// C type : WGPUCallbackMode
	@Field(1) 
	public IntValuedEnum<WGPUCallbackMode > mode() {
		return this.io.getEnumField(this, 1);
	}
	/// C type : WGPUCallbackMode
	@Field(1) 
	public WGPUCreateComputePipelineAsyncCallbackInfo mode(IntValuedEnum<WGPUCallbackMode > mode) {
		this.io.setEnumField(this, 1, mode);
		return this;
	}
	/// C type : WGPUCreateComputePipelineAsyncCallback
	@Field(2) 
	public Pointer<WGPUCreateComputePipelineAsyncCallback > callback() {
		return this.io.getPointerField(this, 2);
	}
	/// C type : WGPUCreateComputePipelineAsyncCallback
	@Field(2) 
	public WGPUCreateComputePipelineAsyncCallbackInfo callback(Pointer<WGPUCreateComputePipelineAsyncCallback > callback) {
		this.io.setPointerField(this, 2, callback);
		return this;
	}
	/// C type : void*
	@Field(3) 
	public Pointer<? > userdata() {
		return this.io.getPointerField(this, 3);
	}
	/// C type : void*
	@Field(3) 
	public WGPUCreateComputePipelineAsyncCallbackInfo userdata(Pointer<? > userdata) {
		this.io.setPointerField(this, 3, userdata);
		return this;
	}
	public WGPUCreateComputePipelineAsyncCallbackInfo(Pointer pointer) {
		super(pointer);
	}
}
