package com.bolsadeideas.springboot.backend.apirest.auth;

public class JwtConfig {

	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	public static final String PRIVATE_RSA = "-----BEGIN PRIVATE KEY-----\r\n" + 
			"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCXKtbFtTnBX+gT\r\n" + 
			"dVqeSSD+aXFa4sJy7q4KLjwGQ/GoOXr2c1KAZGcBZcrgO8PPAi2l80np6L9CjtDB\r\n" + 
			"LoDiV6lg2MU0XXLHEaqjj/2imf/2tDW8wcLF2A3YEKatNQG6mltiNIp0bQlVMcuy\r\n" + 
			"647Opf1AXYDxg61vwuU7Axh6q5GcRElgMM/OUwDinuzOrYJx1CQaWubzzMkM3jxk\r\n" + 
			"p0A3lkQRmxyPiq09DSdnRQjii9QR4kWFf9teYrNvkrh6wiV5wwN6Qqcelv3v+gg1\r\n" + 
			"4GaR1+yiXO7LqtIDoRCCvQgy0LyfK8H7969Ufac+hs1s6nV3fZ0CkerP6+jkfhlu\r\n" + 
			"YTTzGYB5AgMBAAECggEAJpmHPvPAHCsfqPxR19InjR8ilUkKyus8YJgetqDZz5Fh\r\n" + 
			"3xVibyt0C+zQqYJJRQ4y1smxSXunftjV4EY+Hc2xj+u6K11jRgayUlKdusvlCrtI\r\n" + 
			"b3rwf7FkE5Ro4xiCaQIqLIDrC8Cd3fW5pGW20PicmrdligYMHtoey3tGYGh35XU7\r\n" + 
			"1tNloOdV5fCE8kRXwpaIyk3paf4WrhbFY/jt9dpUUS5dJ3H0GqiyvWePbKEdzPYE\r\n" + 
			"yqdZsC2+OIkOl5t1UaDyVZ4griGp2BHdKefaXhcXFFIw/hEzxVtWmYD5/DG+x6QO\r\n" + 
			"rDE1yHXu9VxeLmwvQ+LkvTfe5/gRDAiGTxeUx6wfCwKBgQDMjJ+roO5CFL28Ypwb\r\n" + 
			"N6Y+KfiVCiYPDvOde6I7VPmorg/S+Ulf+9sK0qsXMMB9NmnEGhxfv8DshsZqqLcS\r\n" + 
			"1QZu1ajVuFHp+w8lW+udkjSS4vXqrV9DtCkQ7xGidgozApi23fPs1ZzAKxhm+EPC\r\n" + 
			"PzM2iASffXY4sJ65C+X3fi0skwKBgQC9MNVqc1XZLlNTyzU1CVRAYbbaGDmmqRNz\r\n" + 
			"YmrlOOL1bGvx5UQpZSIWvDPdbA8WHbhocXuKfwsMxwwITCI8F2WTR84rf8vnqotd\r\n" + 
			"DaG22UOtCZ2AeD4sQELY1nVR8IRAui+G30PhbsRGL2IEHBZDe+9O2YLOQ/sXi4Mg\r\n" + 
			"O1n6IpmSQwKBgHpqtqyKtc0KiLsT8QVA+tc9Wy4nUru0YQwhD+ieZIQVGBdVZhpx\r\n" + 
			"qpgBmK9aV/kX9ZiNX+nV4uorhtgSfO5iW2U9rbuqgiucO2ZKaup0s84s0qxOcjMc\r\n" + 
			"wW8HBPDL4DLDHQ5kKzXUaemAYSxO7I52MHo0UT/6YCRxX6yzeD/0XajNAoGBAIBP\r\n" + 
			"AT570CpGB89ZjtFUbWZontlU+qx0GLP1eGfTZ+Fo0eCxS8QDSdY/d7Fg2u514545\r\n" + 
			"8L5iIP1edWOfNsyoVYRIe6P2vTR17qSCXT2kJgjR75a0df4O6wcik1iqFRfM7fS7\r\n" + 
			"sM7QD59F/WSqI9NK12cTj2oSotU6EgknF4PUOXD/AoGAG2psN2lBJtoWKddwp8e3\r\n" + 
			"vWgx4qElTyqYRKZIirY3d9+/Yz+rwC6zqnEGPGqd1o9xrtMqKHut+kL48uRWgsUk\r\n" + 
			"TjYhjO1aYi3cWu985shd5VESdB90ULvbKF3HrArXMgL1241bclTtuSmU3GFTPadl\r\n" + 
			"klPji/ftHbBhaZ3G03GCp0I=\r\n" + 
			"-----END PRIVATE KEY-----";
	
	public static final String PUBLIC_RSA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlyrWxbU5wV/oE3Vankkg\r\n" + 
			"/mlxWuLCcu6uCi48BkPxqDl69nNSgGRnAWXK4DvDzwItpfNJ6ei/Qo7QwS6A4lep\r\n" + 
			"YNjFNF1yxxGqo4/9opn/9rQ1vMHCxdgN2BCmrTUBuppbYjSKdG0JVTHLsuuOzqX9\r\n" + 
			"QF2A8YOtb8LlOwMYequRnERJYDDPzlMA4p7szq2CcdQkGlrm88zJDN48ZKdAN5ZE\r\n" + 
			"EZscj4qtPQ0nZ0UI4ovUEeJFhX/bXmKzb5K4esIlecMDekKnHpb97/oINeBmkdfs\r\n" + 
			"olzuy6rSA6EQgr0IMtC8nyvB+/evVH2nPobNbOp1d32dApHqz+vo5H4ZbmE08xmA\r\n" + 
			"eQIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
	
}
