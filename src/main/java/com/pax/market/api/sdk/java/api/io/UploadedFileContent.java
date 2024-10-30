/**
 * ********************************************************************************
 * COPYRIGHT      
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION     
 *   This software is supplied under the terms of a license agreement or      
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied     
 *   or disclosed except in accordance with the terms in that agreement.
 *         
 *      Copyright (C) 2017 PAX Technology, Inc. All rights reserved.
 * ********************************************************************************
 */

package com.pax.market.api.sdk.java.api.io;

import java.io.*;

public class UploadedFileContent implements Serializable {

	private static final long serialVersionUID = -8646445140451427L;
	private final byte[] bytesContent;
	private final String name;
	private final String originalFilename;
	private final String contentType;

	public UploadedFileContent(byte[] bytesContent, String name, String originalFilename) {
		this(bytesContent, name, originalFilename, null);
	}
	
	public UploadedFileContent(byte[] bytesContent, String name, String originalFilename, String contentType) {
		this.bytesContent = bytesContent;
		this.name = name;
		this.originalFilename = originalFilename;
		this.contentType = contentType;
	}
	
	public byte[] getBytesContent() {
		return bytesContent;
	}

	public String getName() {
		return name;
	}
	
	/**
	 * Return the size of the file in bytes.
	 * @return the size of the file, or 0 if empty
	 */
	public long getSize() {
		if(bytesContent != null) {
			return bytesContent.length;
		}
		return 0;
	}

	public String getContentType() {
		return contentType;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}
}
