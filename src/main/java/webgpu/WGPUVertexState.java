package webgpu;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.bridj.ann.Ptr;
import webgpu.WebgpuLibrary.WGPUShaderModule;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("webgpu") 
public class WGPUVertexState extends StructObject {
	public WGPUVertexState() {
		super();
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public Pointer<WGPUChainedStruct > nextInChain() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : const WGPUChainedStruct*
	@Field(0) 
	public WGPUVertexState nextInChain(Pointer<WGPUChainedStruct > nextInChain) {
		this.io.setPointerField(this, 0, nextInChain);
		return this;
	}
	/// C type : WGPUShaderModule
	@Field(1) 
	public WGPUShaderModule module() {
		return this.io.getTypedPointerField(this, 1);
	}
	/// C type : WGPUShaderModule
	@Field(1) 
	public WGPUVertexState module(WGPUShaderModule module) {
		this.io.setPointerField(this, 1, module);
		return this;
	}
	/// C type : const char*
	@Field(2) 
	public Pointer<Byte > entryPoint() {
		return this.io.getPointerField(this, 2);
	}
	/// C type : const char*
	@Field(2) 
	public WGPUVertexState entryPoint(Pointer<Byte > entryPoint) {
		this.io.setPointerField(this, 2, entryPoint);
		return this;
	}
	@Ptr 
	@Field(3) 
	public long constantCount() {
		return this.io.getSizeTField(this, 3);
	}
	@Ptr 
	@Field(3) 
	public WGPUVertexState constantCount(long constantCount) {
		this.io.setSizeTField(this, 3, constantCount);
		return this;
	}
	/// C type : const WGPUConstantEntry*
	@Field(4) 
	public Pointer<WGPUConstantEntry > constants() {
		return this.io.getPointerField(this, 4);
	}
	/// C type : const WGPUConstantEntry*
	@Field(4) 
	public WGPUVertexState constants(Pointer<WGPUConstantEntry > constants) {
		this.io.setPointerField(this, 4, constants);
		return this;
	}
	@Ptr 
	@Field(5) 
	public long bufferCount() {
		return this.io.getSizeTField(this, 5);
	}
	@Ptr 
	@Field(5) 
	public WGPUVertexState bufferCount(long bufferCount) {
		this.io.setSizeTField(this, 5, bufferCount);
		return this;
	}
	/// C type : const WGPUVertexBufferLayout*
	@Field(6) 
	public Pointer<WGPUVertexBufferLayout > buffers() {
		return this.io.getPointerField(this, 6);
	}
	/// C type : const WGPUVertexBufferLayout*
	@Field(6) 
	public WGPUVertexState buffers(Pointer<WGPUVertexBufferLayout > buffers) {
		this.io.setPointerField(this, 6, buffers);
		return this;
	}
	public WGPUVertexState(Pointer pointer) {
		super(pointer);
	}
}
