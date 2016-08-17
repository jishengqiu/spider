;
define(
		"common:widget/oui/rsa/rsa.config",
		function(_, n, A) {
			var K = "-----BEGIN PUBLIC KEY-----\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDMO0o8vYsqInbD/8uraIdWqP8Y\ncc7KQuLS7w0VbCWocyMRYu582LwzycBOPvbbEKt2feqpUKQ+F3peq+HQnI6gL9d6\n6l0ZG3KjflZTQJ8M847USfUNGVbAi3PJG/NiwQHddUUudmjIEAXwadelp/g+/p97\nYcBAz8caQDcEyI0AjQIDAQAB\n-----END PUBLIC KEY-----;", e = "-----BEGIN PUBLIC KEY-----\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/GP6dENV96jD1oSvmbYhbhHhc\n/7Ngv+DY0iuhmzteyrWDswJDddEHViuhJTZguCGotujOcauzNm71sjW+sHXEJVIX\nHD4eiBNN9SpPpxeioKepaArqujo76GX2EW6JNCBFiB8fevhZHV/e7PQik+jZw/m1\n0eAjakbsbS4TBvwUtwIDAQAB\n-----END PUBLIC KEY-----";
			window.___RSA_TRICK_KEYS___ = {
				onlineKey : K,
				testKey : e
			}, ___RSA_TRICK_KEYS___.finalKey = ___RSA_TRICK_KEYS___.onlineKey,
					A.exports = ___RSA_TRICK_KEYS___.finalKey
		});
