/**
 * Model representing a Location object
 */

Ext.define('kahunja.model.location.Location',{
	extend:'kahunja.model.Base',
	idProperty: 'locCode',
	fields:[
	        {
	        	//Id field
	        	 name: 'locCode',
	             type: 'int',
	             useNull : true
	        },
	        {
	        	 name: 'locShtDesc',
	             type: 'string'
	        },
	        {
	        	 name: 'locDescription',
	             type: 'string'
	        }
	        ],
});