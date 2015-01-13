/**
 * Model representing a Unit object
 */

Ext.define('kahunja.model.units.Unit',{
	extend:'kahunja.model.Base',
	idProperty: 'untCode',
	fields:[
	        {
	        	//Id field
	        	 name: 'untCode',
	             type: 'int',
	             useNull : true
	        },
	        
	        {
	        	 name: 'untShtDesc',
	             type: 'string'
	        },
	        {
	        	 name: 'untDesc',
	             type: 'string'
	        },
	        {	        	
	        	 name: 'untStdPrecision',
	             type: 'int'	            
	        }
	        ],
});