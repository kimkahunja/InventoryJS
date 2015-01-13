/**
 * Model representing a LocationBin object
 */

Ext.define('kahunja.model.location.Bin',{
	extend:'kahunja.model.Base',
	idProperty: 'lbnCode',
	fields:[
	        {
	        	//Id field
	        	 name: 'lbnCode',
	             type: 'int',
	             useNull : true
	        },
	        {
	        	//Fk field
	        	 name: 'lbnLocCode',
	             type: 'int',
	        },
	        {
	        	 name: 'lbnShtDesc',
	             type: 'string'
	        },
	        {
	        	 name: 'lbnDescription',
	             type: 'string'
	        }
	        ],
});