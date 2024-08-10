package wgpu;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import wgpu.WgpuLibrary.WGPUQuerySet;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("wgpu") 
public class WGPUComputePassTimestampWrites extends StructObject {
	public WGPUComputePassTimestampWrites() {
		super();
	}
	/// C type : WGPUQuerySet
	@Field(0) 
	public WGPUQuerySet querySet() {
		return this.io.getTypedPointerField(this, 0);
	}
	/// C type : WGPUQuerySet
	@Field(0) 
	public WGPUComputePassTimestampWrites querySet(WGPUQuerySet querySet) {
		this.io.setPointerField(this, 0, querySet);
		return this;
	}
	@Field(1) 
	public int beginningOfPassWriteIndex() {
		return this.io.getIntField(this, 1);
	}
	@Field(1) 
	public WGPUComputePassTimestampWrites beginningOfPassWriteIndex(int beginningOfPassWriteIndex) {
		this.io.setIntField(this, 1, beginningOfPassWriteIndex);
		return this;
	}
	@Field(2) 
	public int endOfPassWriteIndex() {
		return this.io.getIntField(this, 2);
	}
	@Field(2) 
	public WGPUComputePassTimestampWrites endOfPassWriteIndex(int endOfPassWriteIndex) {
		this.io.setIntField(this, 2, endOfPassWriteIndex);
		return this;
	}
	public WGPUComputePassTimestampWrites(Pointer pointer) {
		super(pointer);
	}
}
