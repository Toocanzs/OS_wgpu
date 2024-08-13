package webgpu;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import webgpu.WebgpuLibrary.WGPUAlphaMode;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("webgpu") 
public class WGPUCopyTextureForBrowserOptions extends StructObject {
	public WGPUCopyTextureForBrowserOptions() {
		super();
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public Pointer<WGPUChainedStruct > nextInChain() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public WGPUCopyTextureForBrowserOptions nextInChain(Pointer<WGPUChainedStruct > nextInChain) {
		this.io.setPointerField(this, 0, nextInChain);
		return this;
	}
	/// C type : WGPUBool
	@Field(1) 
	public int flipY() {
		return this.io.getIntField(this, 1);
	}
	/// C type : WGPUBool
	@Field(1) 
	public WGPUCopyTextureForBrowserOptions flipY(int flipY) {
		this.io.setIntField(this, 1, flipY);
		return this;
	}
	/// C type : WGPUBool
	@Field(2) 
	public int needsColorSpaceConversion() {
		return this.io.getIntField(this, 2);
	}
	/// C type : WGPUBool
	@Field(2) 
	public WGPUCopyTextureForBrowserOptions needsColorSpaceConversion(int needsColorSpaceConversion) {
		this.io.setIntField(this, 2, needsColorSpaceConversion);
		return this;
	}
	/// C type : WGPUAlphaMode
	@Field(3) 
	public IntValuedEnum<WGPUAlphaMode > srcAlphaMode() {
		return this.io.getEnumField(this, 3);
	}
	/// C type : WGPUAlphaMode
	@Field(3) 
	public WGPUCopyTextureForBrowserOptions srcAlphaMode(IntValuedEnum<WGPUAlphaMode > srcAlphaMode) {
		this.io.setEnumField(this, 3, srcAlphaMode);
		return this;
	}
	/// C type : const float*
	@Field(4) 
	public Pointer<Float > srcTransferFunctionParameters() {
		return this.io.getPointerField(this, 4);
	}
	/// C type : const float*
	@Field(4) 
	public WGPUCopyTextureForBrowserOptions srcTransferFunctionParameters(Pointer<Float > srcTransferFunctionParameters) {
		this.io.setPointerField(this, 4, srcTransferFunctionParameters);
		return this;
	}
	/// C type : const float*
	@Field(5) 
	public Pointer<Float > conversionMatrix() {
		return this.io.getPointerField(this, 5);
	}
	/// C type : const float*
	@Field(5) 
	public WGPUCopyTextureForBrowserOptions conversionMatrix(Pointer<Float > conversionMatrix) {
		this.io.setPointerField(this, 5, conversionMatrix);
		return this;
	}
	/// C type : const float*
	@Field(6) 
	public Pointer<Float > dstTransferFunctionParameters() {
		return this.io.getPointerField(this, 6);
	}
	/// C type : const float*
	@Field(6) 
	public WGPUCopyTextureForBrowserOptions dstTransferFunctionParameters(Pointer<Float > dstTransferFunctionParameters) {
		this.io.setPointerField(this, 6, dstTransferFunctionParameters);
		return this;
	}
	/// C type : WGPUAlphaMode
	@Field(7) 
	public IntValuedEnum<WGPUAlphaMode > dstAlphaMode() {
		return this.io.getEnumField(this, 7);
	}
	/// C type : WGPUAlphaMode
	@Field(7) 
	public WGPUCopyTextureForBrowserOptions dstAlphaMode(IntValuedEnum<WGPUAlphaMode > dstAlphaMode) {
		this.io.setEnumField(this, 7, dstAlphaMode);
		return this;
	}
	/// C type : WGPUBool
	@Field(8) 
	public int internalUsage() {
		return this.io.getIntField(this, 8);
	}
	/// C type : WGPUBool
	@Field(8) 
	public WGPUCopyTextureForBrowserOptions internalUsage(int internalUsage) {
		this.io.setIntField(this, 8, internalUsage);
		return this;
	}
	public WGPUCopyTextureForBrowserOptions(Pointer pointer) {
		super(pointer);
	}
}