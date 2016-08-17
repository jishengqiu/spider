function encrypt(t, e) {
    var r, h, c, i, f, g, l, o, s, p, y = [16843776, 0, 65536, 16843780, 16842756, 66564, 4, 65536, 1024, 16843776, 16843780, 1024, 16778244, 16842756, 16777216, 4, 1028, 16778240, 16778240, 66560, 66560, 16842752, 16842752, 16778244, 65540, 16777220, 16777220, 65540, 0, 1028, 66564, 16777216, 65536, 16843780, 4, 16842752, 16843776, 16777216, 16777216, 1024, 16842756, 65536, 66560, 16777220, 1024, 4, 16778244, 66564, 16843780, 65540, 16842752, 16778244, 16777220, 1028, 66564, 16843776, 1028, 16778240, 16778240, 0, 65540, 66560, 0, 16842756], a = [-2146402272, -2147450880, 32768, 1081376, 1048576, 32, -2146435040, -2147450848, -2147483616, -2146402272, -2146402304, -2147483648, -2147450880, 1048576, 32, -2146435040, 1081344, 1048608, -2147450848, 0, -2147483648, 32768, 1081376, -2146435072, 1048608, -2147483616, 0, 1081344, 32800, -2146402304, -2146435072, 32800, 0, 1081376, -2146435040, 1048576, -2147450848, -2146435072, -2146402304, 32768, -2146435072, -2147450880, 32, -2146402272, 1081376, 32, 32768, -2147483648, 32800, -2146402304, 1048576, -2147483616, 1048608, -2147450848, -2147483616, 1048608, 1081344, 0, -2147450880, 32800, -2147483648, -2146435040, -2146402272, 1081344], b = [520, 134349312, 0, 134348808, 134218240, 0, 131592, 134218240, 131080, 134217736, 134217736, 131072, 134349320, 131080, 134348800, 520, 134217728, 8, 134349312, 512, 131584, 134348800, 134348808, 131592, 134218248, 131584, 131072, 134218248, 8, 134349320, 512, 134217728, 134349312, 134217728, 131080, 520, 131072, 134349312, 134218240, 0, 512, 131080, 134349320, 134218240, 134217736, 512, 0, 134348808, 134218248, 131072, 134217728, 134349320, 8, 131592, 131584, 134217736, 134348800, 134218248, 520, 134348800, 131592, 8, 134348808, 131584], C = [8396801, 8321, 8321, 128, 8396928, 8388737, 8388609, 8193, 0, 8396800, 8396800, 8396929, 129, 0, 8388736, 8388609, 1, 8192, 8388608, 8396801, 128, 8388608, 8193, 8320, 8388737, 1, 8320, 8388736, 8192, 8396928, 8396929, 129, 8388736, 8388609, 8396800, 8396929, 129, 0, 0, 8396800, 8320, 8388736, 8388737, 1, 8396801, 8321, 8321, 128, 8396929, 129, 1, 8192, 8388609, 8193, 8396928, 8388737, 8193, 8320, 8388608, 8396801, 128, 8388608, 8192, 8396928], d = [256, 34078976, 34078720, 1107296512, 524288, 256, 1073741824, 34078720, 1074266368, 524288, 33554688, 1074266368, 1107296512, 1107820544, 524544, 1073741824, 33554432, 1074266112, 1074266112, 0, 1073742080, 1107820800, 1107820800, 33554688, 1107820544, 1073742080, 0, 1107296256, 34078976, 33554432, 1107296256, 524544, 524288, 1107296512, 256, 33554432, 1073741824, 34078720, 1107296512, 1074266368, 33554688, 1073741824, 1107820544, 34078976, 1074266368, 256, 33554432, 1107820544, 1107820800, 524544, 1107296256, 1107820800, 34078720, 0, 1074266112, 1107296256, 524544, 33554688, 1073742080, 524288, 0, 1074266112, 34078976, 1073742080], n = [536870928, 541065216, 16384, 541081616, 541065216, 16, 541081616, 4194304, 536887296, 4210704, 4194304, 536870928, 4194320, 536887296, 536870912, 16400, 0, 4194320, 536887312, 16384, 4210688, 536887312, 16, 541065232, 541065232, 0, 4210704, 541081600, 16400, 4210688, 541081600, 536870912, 536887296, 16, 541065232, 4210688, 541081616, 4194304, 16400, 536870928, 4194304, 536887296, 536870912, 16400, 536870928, 541081616, 4210688, 541065216, 4210704, 541081600, 0, 541065232, 16, 16384, 541065216, 4210704, 16384, 4194320, 536887312, 0, 541081600, 536870912, 4194320, 536887312], A = [2097152, 69206018, 67110914, 0, 2048, 67110914, 2099202, 69208064, 69208066, 2097152, 0, 67108866, 2, 67108864, 69206018, 2050, 67110912, 2099202, 2097154, 67110912, 67108866, 69206016, 69208064, 2097154, 69206016, 2048, 2050, 69208066, 2099200, 2, 67108864, 2099200, 67108864, 2099200, 2097152, 67110914, 67110914, 69206018, 69206018, 2, 2097154, 67108864, 67110912, 2097152, 69208064, 2050, 2099202, 69208064, 2050, 67108866, 69208066, 69206016, 2099200, 0, 2, 69208066, 0, 2099202, 69206016, 2048, 67108866, 67110912, 2048, 2097154], u = [268439616, 4096, 262144, 268701760, 268435456, 268439616, 64, 268435456, 262208, 268697600, 268701760, 266240, 268701696, 266304, 4096, 64, 268697600, 268435520, 268439552, 4160, 266240, 262208, 268697664, 268701696, 4160, 0, 0, 268697664, 268435520, 268439552, 266304, 262144, 266304, 262144, 268701696, 4096, 64, 268697664, 4096, 266304, 268439552, 64, 268435520, 268697600, 268697664, 268435456, 262144, 268439616, 0, 268701760, 262208, 268435520, 268697600, 268439552, 268439616, 0, 268701760, 266240, 266240, 4160, 4160, 262208, 268435456, 268701696], m = createKeys(t), x = 0, S = e.length, v = 0, K = 32 == m.length ? 3 : 9;
    for (o = 3 == K ? [0, 32, 2] : [0, 32, 2, 62, 30, -2, 64, 96, 2],
    e += "\x00\x00\x00\x00\x00\x00\x00\x00",
    tempresult = result = ""; S > x; ) {
        for (g = e.charCodeAt(x++) << 16 | e.charCodeAt(x++),
        l = e.charCodeAt(x++) << 16 | e.charCodeAt(x++),
        c = 252645135 & (g >>> 4 ^ l),
        l ^= c,
        g ^= c << 4,
        c = 65535 & (g >>> 16 ^ l),
        l ^= c,
        g ^= c << 16,
        c = 858993459 & (l >>> 2 ^ g),
        g ^= c,
        l ^= c << 2,
        c = 16711935 & (l >>> 8 ^ g),
        g ^= c,
        l ^= c << 8,
        c = 1431655765 & (g >>> 1 ^ l),
        l ^= c,
        g ^= c << 1,
        g = g << 1 | g >>> 31,
        l = l << 1 | l >>> 31,
        h = 0; K > h; h += 3) {
            for (s = o[h + 1],
            p = o[h + 2],
            r = o[h]; r != s; r += p)
                i = l ^ m[r],
                f = (l >>> 4 | l << 28) ^ m[r + 1],
                c = g,
                g = l,
                l = c ^ (a[i >>> 24 & 63] | C[i >>> 16 & 63] | n[i >>> 8 & 63] | u[63 & i] | y[f >>> 24 & 63] | b[f >>> 16 & 63] | d[f >>> 8 & 63] | A[63 & f]);
            c = g,
            g = l,
            l = c
        }
        g = g >>> 1 | g << 31,
        l = l >>> 1 | l << 31,
        c = 1431655765 & (g >>> 1 ^ l),
        l ^= c,
        g ^= c << 1,
        c = 16711935 & (l >>> 8 ^ g),
        g ^= c,
        l ^= c << 8,
        c = 858993459 & (l >>> 2 ^ g),
        g ^= c,
        l ^= c << 2,
        c = 65535 & (g >>> 16 ^ l),
        l ^= c,
        g ^= c << 16,
        c = 252645135 & (g >>> 4 ^ l),
        l ^= c,
        g ^= c << 4,
        tempresult += String.fromCharCode(g >>> 24, g >>> 16 & 255, g >>> 8 & 255, 255 & g, l >>> 24, l >>> 16 & 255, l >>> 8 & 255, 255 & l),
        v += 16,
        512 == v && (result += tempresult,
        tempresult = "",
        v = 0)
    }
    return result + tempresult
}
function createKeys(t) {
    pc2bytes0 = [0, 4, 536870912, 536870916, 65536, 65540, 536936448, 536936452, 512, 516, 536871424, 536871428, 66048, 66052, 536936960, 536936964],
    pc2bytes1 = [0, 1, 1048576, 1048577, 67108864, 67108865, 68157440, 68157441, 256, 257, 1048832, 1048833, 67109120, 67109121, 68157696, 68157697],
    pc2bytes2 = [0, 8, 2048, 2056, 16777216, 16777224, 16779264, 16779272, 0, 8, 2048, 2056, 16777216, 16777224, 16779264, 16779272],
    pc2bytes3 = [0, 2097152, 134217728, 136314880, 8192, 2105344, 134225920, 136323072, 131072, 2228224, 134348800, 136445952, 139264, 2236416, 134356992, 136454144],
    pc2bytes4 = [0, 262144, 16, 262160, 0, 262144, 16, 262160, 4096, 266240, 4112, 266256, 4096, 266240, 4112, 266256],
    pc2bytes5 = [0, 1024, 32, 1056, 0, 1024, 32, 1056, 33554432, 33555456, 33554464, 33555488, 33554432, 33555456, 33554464, 33555488],
    pc2bytes6 = [0, 268435456, 524288, 268959744, 2, 268435458, 524290, 268959746, 0, 268435456, 524288, 268959744, 2, 268435458, 524290, 268959746],
    pc2bytes7 = [0, 65536, 2048, 67584, 536870912, 536936448, 536872960, 536938496, 131072, 196608, 133120, 198656, 537001984, 537067520, 537004032, 537069568],
    pc2bytes8 = [0, 262144, 0, 262144, 2, 262146, 2, 262146, 33554432, 33816576, 33554432, 33816576, 33554434, 33816578, 33554434, 33816578],
    pc2bytes9 = [0, 268435456, 8, 268435464, 0, 268435456, 8, 268435464, 1024, 268436480, 1032, 268436488, 1024, 268436480, 1032, 268436488],
    pc2bytes10 = [0, 32, 0, 32, 1048576, 1048608, 1048576, 1048608, 8192, 8224, 8192, 8224, 1056768, 1056800, 1056768, 1056800],
    pc2bytes11 = [0, 16777216, 512, 16777728, 2097152, 18874368, 2097664, 18874880, 67108864, 83886080, 67109376, 83886592, 69206016, 85983232, 69206528, 85983744],
    pc2bytes12 = [0, 4096, 134217728, 134221824, 524288, 528384, 134742016, 134746112, 16, 4112, 134217744, 134221840, 524304, 528400, 134742032, 134746128],
    pc2bytes13 = [0, 4, 256, 260, 0, 4, 256, 260, 1, 5, 257, 261, 1, 5, 257, 261],
    t += String.fromCharCode(104),
    t += String.fromCharCode(110),
    t += String.fromCharCode(105),
    t += String.fromCharCode(115),
    t += String.fromCharCode(105);
    for (var e, r, h, c = t.length >= 24 ? 3 : 1, f = Array(32 * c), g = [0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0], l = 0, o = 0, s = 0; c > s; s++)
        for (left = t.charCodeAt(l++) << 24 | t.charCodeAt(l++) << 16 | t.charCodeAt(l++) << 8 | t.charCodeAt(l++),
        right = t.charCodeAt(l++) << 24 | t.charCodeAt(l++) << 16 | t.charCodeAt(l++) << 8 | t.charCodeAt(l++),
        h = 252645135 & (left >>> 4 ^ right),
        right ^= h,
        left ^= h << 4,
        h = 65535 & (right >>> -16 ^ left),
        left ^= h,
        right ^= h << -16,
        h = 858993459 & (left >>> 2 ^ right),
        right ^= h,
        left ^= h << 2,
        h = 65535 & (right >>> -16 ^ left),
        left ^= h,
        right ^= h << -16,
        h = 1431655765 & (left >>> 1 ^ right),
        right ^= h,
        left ^= h << 1,
        h = 16711935 & (right >>> 8 ^ left),
        left ^= h,
        right ^= h << 8,
        h = 1431655765 & (left >>> 1 ^ right),
        right ^= h,
        left ^= h << 1,
        h = left << 8 | right >>> 20 & 240,
        left = right << 24 | right << 8 & 16711680 | right >>> 8 & 65280 | right >>> 24 & 240,
        right = h,
        i = 0; i < g.length; i++)
            g[i] ? (left = left << 2 | left >>> 26,
            right = right << 2 | right >>> 26) : (left = left << 1 | left >>> 27,
            right = right << 1 | right >>> 27),
            left &= -15,
            right &= -15,
            e = pc2bytes0[left >>> 28] | pc2bytes1[left >>> 24 & 15] | pc2bytes2[left >>> 20 & 15] | pc2bytes3[left >>> 16 & 15] | pc2bytes4[left >>> 12 & 15] | pc2bytes5[left >>> 8 & 15] | pc2bytes6[left >>> 4 & 15],
            r = pc2bytes7[right >>> 28] | pc2bytes8[right >>> 24 & 15] | pc2bytes9[right >>> 20 & 15] | pc2bytes10[right >>> 16 & 15] | pc2bytes11[right >>> 12 & 15] | pc2bytes12[right >>> 8 & 15] | pc2bytes13[right >>> 4 & 15],
            h = 65535 & (r >>> 16 ^ e),
            f[o++] = e ^ h,
            f[o++] = r ^ h << 16;
    return f
}
function stringToHex(t) {
    for (var e = "", r = "", h = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"], c = 0; c < t.length; c++)
        0 == c ? e = t.charCodeAt(c) : e += ":" + t.charCodeAt(c);
    for (c = 0; c < e.length; c++)
        r += h[e.charCodeAt(c) >> 4] + h[15 & e.charCodeAt(c)];
    return r
}