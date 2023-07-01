from Crypto.Cipher import AES
from Crypto.Util.Padding import pad, unpad
import binascii

class Encrypt:

    def encrypt_ecb_aes(self, plaintext, key = "miaproyecto12345"):
        key_bytes = key.encode('utf-8')
        plaintext_bytes = plaintext.encode('utf-8')

        cipher = AES.new(key_bytes, AES.MODE_ECB)

        padded_plaintext = pad(plaintext_bytes, AES.block_size)

        ciphertext_bytes = cipher.encrypt(padded_plaintext)

        ciphertext_hex = binascii.hexlify(ciphertext_bytes).decode('utf-8')

        return ciphertext_hex

    def decrypt_ecb_aes(self, ciphertext, key = "miaproyecto12345"):
        key_bytes = key.encode('utf-8')
        ciphertext_bytes = binascii.unhexlify(ciphertext)

        cipher = AES.new(key_bytes, AES.MODE_ECB)

        decrypted_bytes = cipher.decrypt(ciphertext_bytes)

        plaintext_bytes = unpad(decrypted_bytes, AES.block_size)

        plaintext = plaintext_bytes.decode('utf-8')

        return plaintext

def encrypt_ecb_aes(self, plaintext, key = "miaproyecto12345"):
        key_bytes = key.encode('utf-8')
        plaintext_bytes = plaintext.encode('utf-8')

        cipher = AES.new(key_bytes, AES.MODE_ECB)

        padded_plaintext = pad(plaintext_bytes, AES.block_size)

        ciphertext_bytes = cipher.encrypt(padded_plaintext)

        ciphertext_hex = binascii.hexlify(ciphertext_bytes).decode('utf-8')

        return ciphertext_hex




# encriptar = Encrypt()
# encriptado = encriptar.encrypt_ecb_aes("password1234")
# print(encriptado)
# print(encriptar.decrypt_ecb_aes("405af6813a5e62dc3875dc69ae4d3ec8281467bb5a96c6c60cc8cbd8aebb4b0b67e2faffb6efbd1b29e30f58bb85c663b9bcc8eb75b1366c628bbbadd3d3d4adbd429e0058f090672452a9b63d1276a7ef2c96704c0f4881efc04ea60901b963d3786b1c1f1a4ac47fe7a700e0db2fc8a43fd6caf61dfca07eabe1daca97f2e6e71fbe30d5191649a02d5a7aff2ff1ba024af343304897338daf4150ffba65fe6d53b8ab6c2fa7371eb249b47601fbd07bb5b45cf837f0a394cf7b4ade4896d0229faa6ba6550d89dc627e291b95a625deeda49c4db00f173066474f4ab9db5ee0ebb6c91e797cb730906a7eb1549f03cd7e59f04785e86178b54a0fd34cca1d99d1e25bf50f04944eda54b997c3c70f90736dbfdad84d00bb30759ef996a0bf30661e99655401da95bbe70bd51e755b0695a258df965fefbc9a694326c9618cd976d40ad5692b87df46943cacd2bed72d3e2cfc5985a109ff80fbc7e4758cd7adf439f4b2ce06feee18eaf2b3798c75990e5bc18694603bccd7ccab2384ad81d0884e22991b848201757e7b4ef5bcab214832fe89cd57fbeefa16900c1bb6c12598a2bc448cf1419cf1587b258463665e483d312ea944cb7f87805d77460ef4a96448221bdb0b9c3eff7960cc87f123b2df197783891c5f90bc4999bb9df73c4ec94eb12405712996cef65190912790c9b1be70425f010ccb6a783006a90106f993263aceb88feacc523a5bb8cc13d41c247876f09e75cbd11937240d61d232b8f4be02eda962a26cc8bb5db66acc33abc050c9b8b286671e6c5ea7b2440570b9b3839bdea1008fbbd4012c19a101834619859c5e4e571d6068d54040f756cd726fd2db9d874b5597f4bbab2f86e11e0a4516820074be4d3e58b532024190fca23925626c59d7aa22ad95b7a8ecedfdca465fa097ee39a66c2477d5109032889f756816e6ad3021a101dec878245c2e064c34f1e4b35ac59dc9ae367ad6acb57ea41b0185832795cc9d75c0b913e3de7eb2138eaaafa4468b91f5f5f4c7aac6aa4bbf35da824770052873f61a77ff1de1ada1830a06bd42b813e51cd723ec3b56f4f18e100af9af50215dd2001d97af1fb24ba7a4c65bbe5b729c7dd9f2e546ff89388d70e4b6d7c5088a69a4e70217e4798d407fcfe19aa7e52de52d613ffa7797c12d1ed719e5c8cd5bb09ef5aa37e584f64efe64ed2483578cdcdb600d442296ec57f8786ac42ba22395a0253a8756f6decd6d7e8bca09f17a271d074727069c42bb9852af359d81970b7335a7ce9a47b0615a3bfdae2fb00137178a34765ebe54abebff52b7a346bd9cd963910540b919a7b692b66db3460bd9d118f9d9977d3691c8e54572108cf108f7929e373044e21fdabf2fb9942a35aee86b703a0498e59c134bfbf1f96a3a0386863eda7be511f61cda2d7ae591dfcfab866f8b651b22e7b22174e9bef3178201d96fd918d8fbdb498c71fc31fa10646152f2574b8616c5b828d026ae13f9a05691a737ad2a5f823681ce6b77cdcc04978325284c5860dd7bc37481b0198eacb27c74e5e8fbd0e423696a647870a489bb74e6e82fe10f5e4cdd08ba9dab2af7d12ef882017b65be08ec107b6d6aa08c4dd50da15d7ac19f1b783c1415c34fbb8af5e619dc71302a3d223f157fb6d3014423bb4a891655e98aef7a30ae3b9b0cd7e4f9e2a348e57f8b76665841c26cf2155e41beb56392815c4edf3cf0769099387800e8947caaf578c494f98cfd24fbb3bdca7952cd292717861ed8c336dc91c63ce38a99d839d5769a84dedd89266fa27be723ad30b9046f9d761c0980edbb66126fd59905098eaabc61fd6096a3f3597307d09ad2807012a814ba63ff9dec8293426e489a812a55f33bb37c949f85869062755fcc5b32c184bfc32a1e5da9130dd84ad0122f3f34a12c1422f870e4a1f523fd462220b20a695afea6eda920ed7e2ecc6548b99094c41076a645f8b09a9a1562cc1443f6b32428ab250b071fb6e638edf5a8ed700a6b29a1be8013656bbe1823000bf0ddf3b22e726b1322fd7da9a37fbfab34bf97c129fd5d5016669403ad7ff382367d6494ec5d6075d175526f37a3f7c9dc87da7e0d4fb6c12f174efbc1281ca0b59a4da8891a84353583c38ba21a4f1a5bdcea2ae1b42dfbbb41d0dd357f44e2336391b6056745872f98a303c3179c7db47dea9ae208e37fc3e5dbd8264c2d9787fae6f5f831c2bfba77be38c03d204ac042f5e0e35864878585d88ac01acd9f8bec1eb442a87e625c48c673e10a8b494543528ada9498688a93c422b478a0b5f4869c6ebd3e69ab8c43634350e92e303cea53c14df92ea8debf11f01e1ed4d188d8bcf191c71440b799e261348186208379f1ade4e6e0e07481706a71788e867d6099b662c8d8f059b718413f5cdb30f9cb6c701242ad1cab5f90bd4d345f23004d801ff88d3358a83749a5a179da7ef8bd94a0399d97e1a1dfdbec42d73f07784dfeeac4c8a14173fc818c65d386208379f1ade4e6e0e07481706a717812d40b17ee492ce701c82e5d7eedd412db30f9cb6c701242ad1cab5f90bd4d345f23004d801ff88d3358a83749a5a179da7ef8bd94a0399d97e1a1dfdbec42d7e019abf57118a7f9ae8d08c1375c66f186208379f1ade4e6e0e07481706a7178cb4a468113a12b4a46abc97759544146db30f9cb6c701242ad1cab5f90bd4d345f23004d801ff88d3358a83749a5a179da7ef8bd94a0399d97e1a1dfdbec42d7fadd15c28766a4397580367d6ad7aa83b472cab9534c54ae6b678f49728470677435416f9c12d9fbb0c12673753d9020d12398e34274bb9d69c24b3d9c3fe86b44ea99453df67ddad11e12c5e859bae44ec3f030751db8ff059665d554b56778"))