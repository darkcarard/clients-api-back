package co.darksquirrelsoftware.springboot.backend.apirest.security;

public class JwtConfig {

	public static final String SIGNING_KEY = "Cl@veSecretA*12345?";
	
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA29WQz9vKrrfWprelqQZd\r\n" + 
			"oAvP0S/zmMYu/AqikghTA0QR/BjCXuFvlcXNmlCWeOdJRCNDGNhvUpFUp5yTL2vT\r\n" + 
			"PY+5254lDGbkn8jQPnGKDW5cXdv7zCLZiakxnZ1s7LynW2aDIMEnfKc3JV8GBtXK\r\n" + 
			"4XHaNpR4+Bb1iDWNNHV976cXagcTolqDOrjrnT5xgv2snpqOXko7G9StBb16yN7V\r\n" + 
			"9AcZiB1MD1gGapzJThXVwmiWhQ43dzFXnCNn8eOunz6NgUPbxo0PUA7pX2WqLCPE\r\n" + 
			"HOKfp4JKW6yQcV/94D6SLW076ywZ7oLOXW/R2Zku+G0hVETVUn5OJQ/sy4O3ZPlw\r\n" + 
			"4wIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
	
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpQIBAAKCAQEA29WQz9vKrrfWprelqQZdoAvP0S/zmMYu/AqikghTA0QR/BjC\r\n" + 
			"XuFvlcXNmlCWeOdJRCNDGNhvUpFUp5yTL2vTPY+5254lDGbkn8jQPnGKDW5cXdv7\r\n" + 
			"zCLZiakxnZ1s7LynW2aDIMEnfKc3JV8GBtXK4XHaNpR4+Bb1iDWNNHV976cXagcT\r\n" + 
			"olqDOrjrnT5xgv2snpqOXko7G9StBb16yN7V9AcZiB1MD1gGapzJThXVwmiWhQ43\r\n" + 
			"dzFXnCNn8eOunz6NgUPbxo0PUA7pX2WqLCPEHOKfp4JKW6yQcV/94D6SLW076ywZ\r\n" + 
			"7oLOXW/R2Zku+G0hVETVUn5OJQ/sy4O3ZPlw4wIDAQABAoIBAQDYaa1OwxwL1lUM\r\n" + 
			"+PuvouGmfhpO3/raKjf0zW9vfZxjOa7O03z9tMaJ1hlq0ezgByicWG5kViV5TjLd\r\n" + 
			"col/BJIHJ6ebKVtlkyF8n09KObHjOQLbL4X5V1F6cMK0XFbv02SZ2Rg7tYru7xqd\r\n" + 
			"wqcHMY6xU8izNITGDqwWNEnXG9cnhS6vlC5gbgmU6JYCjot5dS6iICFrqacPEbiO\r\n" + 
			"SU5nh0oJ4FtsmW/SikIEWmWtCPuCdsAiHpUmgdqr4IP+XczLY9FGQ50jHcLkpNaS\r\n" + 
			"waDye6bPfwnp3gY5QqlSqrRYFSA+rMox+QlUJh8W+tJ4jDeR9VT6olfF/1j39cW5\r\n" + 
			"FiBDJAt5AoGBAO+zJ+8H9Cq2Bo3q2iljLnEoG1kwx20CQbqfDTuFLGhnDk/Ktxzv\r\n" + 
			"IBl0hG+N55AiOHag0OZEdql3GExZ0CxAFJ5pvZlxAD1oDCTCMitqI6ZcQjCtqMai\r\n" + 
			"y9WH8rY448buis3Te0Toy/NKeVaTu1x6yWYzuIrMVKbr1SETrpHmrFQFAoGBAOrI\r\n" + 
			"k8COjsuDfGDh7fMTRavQZkx7exapr82jWNWhY6yBZVBsjIA5C6s947Yxt4p5zAzz\r\n" + 
			"/EcKNF5XqWfSnr9yE5qK5Zt2mHU4rR99VXIx4dwF2BonT4hmNcnmw6dz5Hr5kXwF\r\n" + 
			"XqyyAjfQAQrcCePISvvvjO+vPQhluCT7SMlypm3HAoGBAMTgzSsKyGUQb1+/h4M5\r\n" + 
			"AJtWyue+Jf8GPICRRSgfITN7egjVwwNT8XSW/HQ4BPug7+mX+x03HJPiyyVjG5ZM\r\n" + 
			"5+KZjW5rnzvQqzDCBL0eB7wLyR38dUT50BxJ9nPvVD9ADOg0DOqzn1jsUa9n1huG\r\n" + 
			"iseUMb5nSBx2Ve8+IEOcSO0VAoGABFTI7P90QOrfC4Z/dkF/woioqnaIscVVmeyO\r\n" + 
			"gfplNVR5wv8NpHvo7DLLFZxbL3HJwt9pYwIcLzVcya6IAS4gkHRJghGxBFCUWQVd\r\n" + 
			"4ypqaT2uW+UnwXNpQJ1CNL+dkmsyNOCAe50ZD2CF9GZUxF/4iidEKVPJQ3Nk70G3\r\n" + 
			"eQflbBcCgYEA1VwgUb/j+FcuQ34qU/cXfcF5A/EZWBAjSvoXoL4NwyyRCQBo4hk0\r\n" + 
			"v7TDOkCkKPxhhMMmTkay3QP0kAeURyxqEY26JphEb/daH3qOMzqgjBSuJTfJLkV3\r\n" + 
			"56ACcis0qYn/t7rErNRQiTq3kVwMZ664EykUzftTyPfIorwkUPhbYjg=\r\n" + 
			"-----END RSA PRIVATE KEY-----";
}
