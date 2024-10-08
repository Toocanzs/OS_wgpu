package webgpu;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.bridj.ann.Ptr;
import webgpu.WebgpuLibrary.WGPUBindGroupLayout;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("webgpu") 
public class WGPUPipelineLayoutDescriptor extends StructObject {
	public WGPUPipelineLayoutDescriptor() {
		super();
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public Pointer<WGPUChainedStruct > nextInChain() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public WGPUPipelineLayoutDescriptor nextInChain(Pointer<WGPUChainedStruct > nextInChain) {
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
	public WGPUPipelineLayoutDescriptor label(Pointer<Byte > label) {
		this.io.setPointerField(this, 1, label);
		return this;
	}
	@Ptr 
	@Field(2) 
	public long bindGroupLayoutCount() {
		return this.io.getSizeTField(this, 2);
	}
	@Ptr 
	@Field(2) 
	public WGPUPipelineLayoutDescriptor bindGroupLayoutCount(long bindGroupLayoutCount) {
		this.io.setSizeTField(this, 2, bindGroupLayoutCount);
		return this;
	}
	/// C type : const WGPUBindGroupLayout*
	@Field(3) 
	public Pointer<WGPUBindGroupLayout > bindGroupLayouts() {
		return this.io.getPointerField(this, 3);
	}
	/// C type : const WGPUBindGroupLayout*
	@Field(3) 
	public WGPUPipelineLayoutDescriptor bindGroupLayouts(Pointer<WGPUBindGroupLayout > bindGroupLayouts) {
		this.io.setPointerField(this, 3, bindGroupLayouts);
		return this;
	}
	public WGPUPipelineLayoutDescriptor(Pointer pointer) {
		super(pointer);
	}
}
