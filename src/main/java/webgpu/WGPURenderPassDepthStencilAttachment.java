package webgpu;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import webgpu.WebgpuLibrary.WGPULoadOp;
import webgpu.WebgpuLibrary.WGPUStoreOp;
import webgpu.WebgpuLibrary.WGPUTextureView;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("webgpu") 
public class WGPURenderPassDepthStencilAttachment extends StructObject {
	public WGPURenderPassDepthStencilAttachment() {
		super();
	}
	/// C type : WGPUTextureView
	@Field(0) 
	public WGPUTextureView view() {
		return this.io.getTypedPointerField(this, 0);
	}
	/// C type : WGPUTextureView
	@Field(0) 
	public WGPURenderPassDepthStencilAttachment view(WGPUTextureView view) {
		this.io.setPointerField(this, 0, view);
		return this;
	}
	/// C type : WGPULoadOp
	@Field(1) 
	public IntValuedEnum<WGPULoadOp > depthLoadOp() {
		return this.io.getEnumField(this, 1);
	}
	/// C type : WGPULoadOp
	@Field(1) 
	public WGPURenderPassDepthStencilAttachment depthLoadOp(IntValuedEnum<WGPULoadOp > depthLoadOp) {
		this.io.setEnumField(this, 1, depthLoadOp);
		return this;
	}
	/// C type : WGPUStoreOp
	@Field(2) 
	public IntValuedEnum<WGPUStoreOp > depthStoreOp() {
		return this.io.getEnumField(this, 2);
	}
	/// C type : WGPUStoreOp
	@Field(2) 
	public WGPURenderPassDepthStencilAttachment depthStoreOp(IntValuedEnum<WGPUStoreOp > depthStoreOp) {
		this.io.setEnumField(this, 2, depthStoreOp);
		return this;
	}
	@Field(3) 
	public float depthClearValue() {
		return this.io.getFloatField(this, 3);
	}
	@Field(3) 
	public WGPURenderPassDepthStencilAttachment depthClearValue(float depthClearValue) {
		this.io.setFloatField(this, 3, depthClearValue);
		return this;
	}
	/// C type : WGPUBool
	@Field(4) 
	public int depthReadOnly() {
		return this.io.getIntField(this, 4);
	}
	/// C type : WGPUBool
	@Field(4) 
	public WGPURenderPassDepthStencilAttachment depthReadOnly(int depthReadOnly) {
		this.io.setIntField(this, 4, depthReadOnly);
		return this;
	}
	/// C type : WGPULoadOp
	@Field(5) 
	public IntValuedEnum<WGPULoadOp > stencilLoadOp() {
		return this.io.getEnumField(this, 5);
	}
	/// C type : WGPULoadOp
	@Field(5) 
	public WGPURenderPassDepthStencilAttachment stencilLoadOp(IntValuedEnum<WGPULoadOp > stencilLoadOp) {
		this.io.setEnumField(this, 5, stencilLoadOp);
		return this;
	}
	/// C type : WGPUStoreOp
	@Field(6) 
	public IntValuedEnum<WGPUStoreOp > stencilStoreOp() {
		return this.io.getEnumField(this, 6);
	}
	/// C type : WGPUStoreOp
	@Field(6) 
	public WGPURenderPassDepthStencilAttachment stencilStoreOp(IntValuedEnum<WGPUStoreOp > stencilStoreOp) {
		this.io.setEnumField(this, 6, stencilStoreOp);
		return this;
	}
	@Field(7) 
	public int stencilClearValue() {
		return this.io.getIntField(this, 7);
	}
	@Field(7) 
	public WGPURenderPassDepthStencilAttachment stencilClearValue(int stencilClearValue) {
		this.io.setIntField(this, 7, stencilClearValue);
		return this;
	}
	/// C type : WGPUBool
	@Field(8) 
	public int stencilReadOnly() {
		return this.io.getIntField(this, 8);
	}
	/// C type : WGPUBool
	@Field(8) 
	public WGPURenderPassDepthStencilAttachment stencilReadOnly(int stencilReadOnly) {
		this.io.setIntField(this, 8, stencilReadOnly);
		return this;
	}
	public WGPURenderPassDepthStencilAttachment(Pointer pointer) {
		super(pointer);
	}
}