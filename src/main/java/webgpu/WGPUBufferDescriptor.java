package webgpu;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("webgpu") 
public class WGPUBufferDescriptor extends StructObject {
	public WGPUBufferDescriptor() {
		super();
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public Pointer<WGPUChainedStruct > nextInChain() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public WGPUBufferDescriptor nextInChain(Pointer<WGPUChainedStruct > nextInChain) {
		this.io.setPointerField(this, 0, nextInChain);
		return this;
	}
	/// C type : const char*
	@Field(1) 
	public Pointer<Byte > label() {
		return this.io.getPointerField(this, 1);
	}
	/// C type : const char*
	@Field(1) 
	public WGPUBufferDescriptor label(Pointer<Byte > label) {
		this.io.setPointerField(this, 1, label);
		return this;
	}
	/// C type : WGPUBufferUsage
	@Field(2) 
	public long usage() {
		return this.io.getLongField(this, 2);
	}
	/// C type : WGPUBufferUsage
	@Field(2) 
	public WGPUBufferDescriptor usage(long usage) {
		this.io.setLongField(this, 2, usage);
		return this;
	}
	@Field(3) 
	public long size() {
		return this.io.getLongField(this, 3);
	}
	@Field(3) 
	public WGPUBufferDescriptor size(long size) {
		this.io.setLongField(this, 3, size);
		return this;
	}
	/// C type : WGPUBool
	@Field(4) 
	public int mappedAtCreation() {
		return this.io.getIntField(this, 4);
	}
	/// C type : WGPUBool
	@Field(4) 
	public WGPUBufferDescriptor mappedAtCreation(int mappedAtCreation) {
		this.io.setIntField(this, 4, mappedAtCreation);
		return this;
	}
	public WGPUBufferDescriptor(Pointer pointer) {
		super(pointer);
	}
}
