/* 
 *  Copyright (C) 2012 TagServlet Ltd
 *
 *  This file is part of Open BlueDragon (OpenBD) CFML Server Engine.
 *  
 *  OpenBD is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  Free Software Foundation,version 3.
 *  
 *  OpenBD is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with OpenBD.  If not, see http://www.gnu.org/licenses/
 *  
 *  Additional permission under GNU GPL version 3 section 7
 *  
 *  If you modify this Program, or any covered work, by linking or combining 
 *  it with any of the JARS listed in the README.txt (or a modified version of 
 *  (that library), containing parts covered by the terms of that JAR, the 
 *  licensors of this Program grant you additional permission to convey the 
 *  resulting work. 
 *  README.txt @ http://www.openbluedragon.org/license/README.txt
 *  
 *  http://www.openbluedragon.org/
 *  
 *  $Id: CFSavecontentStatement.java 2527 2015-02-26 16:08:24Z andy $
 */
package com.naryx.tagfusion.cfm.parser.script;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;

import org.antlr.runtime.Token;

import com.naryx.tagfusion.cfm.engine.cfmRunTimeException;
import com.naryx.tagfusion.cfm.parser.CFContext;
import com.naryx.tagfusion.cfm.parser.CFExpression;
import com.naryx.tagfusion.cfm.tag.cfTag;


public class CFSavecontentStatement extends CFParsedAttributeStatement implements Serializable {

	private static final long serialVersionUID = 1L;

	private CFScriptStatement body;

	private static HashSet<String> supportedAttributes;

	static {
		supportedAttributes = new HashSet<String>();
		supportedAttributes.add( "VARIABLE" );
	}


	public CFSavecontentStatement( Token _t, Map<String, CFExpression> _attr, CFScriptStatement _body ) {
		super( _t, _attr );

		body = _body;
	}


	@Override
	public CFStatementResult Exec( CFContext context ) throws cfmRunTimeException {
		setLineCol( context );

		String variable = getAttributeValue( context, "VARIABLE", true ).getString();

		// TODO: implementation

		return null;
	}


	@Override
	public void setHostTag( cfTag _parentTag ) {
		super.setHostTag( _parentTag );
		body.setHostTag( _parentTag );
	}


	@Override
	public String Decompile( int indent ) {
		StringBuilder sb = new StringBuilder();
		sb.append( "savecontent " );
		DecompileAttributes( sb );
		if ( body == null ) {
			sb.append( ";" );
		} else {
			sb.append( body.Decompile( 0 ) );
		}

		return sb.toString();
	}

}
