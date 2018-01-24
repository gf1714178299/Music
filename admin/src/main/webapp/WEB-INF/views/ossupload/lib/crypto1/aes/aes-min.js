/*
 * Crypto-JS v1.1.0
 * http://code.google.com/p/crypto-js/
 * Copyright (c) 2009, Jeff Mott. All rights reserved.
 * http://code.google.com/p/crypto-js/wiki/License
 */
(function(){var k=Crypto.util;var e=[99,124,119,123,242,107,111,197,48,1,103,43,254,215,171,118,202,130,201,125,250,89,71,240,173,212,162,175,156,164,114,192,183,253,147,38,54,63,247,204,52,165,229,241,113,216,49,21,4,199,35,195,24,150,5,154,7,18,128,226,235,39,178,117,9,131,44,26,27,110,90,160,82,59,214,179,41,227,47,132,83,209,0,237,32,252,177,91,106,203,190,57,74,76,88,207,208,239,170,251,67,77,51,133,69,249,2,127,80,60,159,168,81,163,64,143,146,157,56,245,188,182,218,33,16,255,243,210,205,12,19,236,95,151,68,23,196,167,126,61,100,93,25,115,96,129,79,220,34,42,144,136,70,238,184,20,222,94,11,219,224,50,58,10,73,6,36,92,194,211,172,98,145,149,228,121,231,200,55,109,141,213,78,169,108,86,244,234,101,122,174,8,186,120,37,46,28,166,180,198,232,221,116,31,75,189,139,138,112,62,181,102,72,3,246,14,97,53,87,185,134,193,29,158,225,248,152,17,105,217,142,148,155,30,135,233,206,85,40,223,140,161,137,13,191,230,66,104,65,153,45,15,176,84,187,22];for(var j=[],g=0;g<256;g++){j[e[g]]=g}var c=[],b=[],q=[],n=[],h=[],f=[];function p(u,t){for(var s=0,v=0;v<8;v++){if(t&1){s^=u}var w=u&128;u=(u<<1)&255;if(w){u^=27}t>>>=1}return s}for(var g=0;g<256;g++){c[g]=p(g,2);b[g]=p(g,3);q[g]=p(g,9);n[g]=p(g,11);h[g]=p(g,13);f[g]=p(g,14)}var d=[0,1,2,4,8,16,32,64,128,27,54];var a=[[],[],[],[]],r,o,m;var l=Crypto.AES={encrypt:function(v,u,w){var i=k.stringToBytes(v),t=k.randomBytes(l._blocksize*4),s=Crypto.PBKDF2(u,k.bytesToString(t),32,{asBytes:true});w=w||Crypto.mode.OFB;l._init(s);w.encrypt(l,i,t);return k.bytesToBase64(t.concat(i))},decrypt:function(u,t,v){var w=k.base64ToBytes(u),s=w.splice(0,l._blocksize*4),i=Crypto.PBKDF2(t,k.bytesToString(s),32,{asBytes:true});v=v||Crypto.mode.OFB;l._init(i);v.decrypt(l,w,s);return k.bytesToString(w)},_blocksize:4,_encryptblock:function(s,t){for(var z=0;z<l._blocksize;z++){for(var i=0;i<4;i++){a[z][i]=s[t+i*4+z]}}for(var z=0;z<4;z++){for(var i=0;i<4;i++){a[z][i]^=m[i][z]}}for(var y=1;y<o;y++){for(var z=0;z<4;z++){for(var i=0;i<4;i++){a[z][i]=e[a[z][i]]}}a[1].push(a[1].shift());a[2].push(a[2].shift());a[2].push(a[2].shift());a[3].unshift(a[3].pop());for(var i=0;i<4;i++){var x=a[0][i],w=a[1][i],v=a[2][i],u=a[3][i];a[0][i]=c[x]^b[w]^v^u;a[1][i]=x^c[w]^b[v]^u;a[2][i]=x^w^c[v]^b[u];a[3][i]=b[x]^w^v^c[u]}for(var z=0;z<4;z++){for(var i=0;i<4;i++){a[z][i]^=m[y*4+i][z]}}}for(var z=0;z<4;z++){for(var i=0;i<4;i++){a[z][i]=e[a[z][i]]}}a[1].push(a[1].shift());a[2].push(a[2].shift());a[2].push(a[2].shift());a[3].unshift(a[3].pop());for(var z=0;z<4;z++){for(var i=0;i<4;i++){a[z][i]^=m[o*4+i][z]}}for(var z=0;z<l._blocksize;z++){for(var i=0;i<4;i++){s[t+i*4+z]=a[z][i]}}},_decryptblock:function(t,s){for(var z=0;z<l._blocksize;z++){for(var i=0;i<4;i++){a[z][i]=t[s+i*4+z]}}for(var z=0;z<4;z++){for(var i=0;i<4;i++){a[z][i]^=m[o*4+i][z]}}for(var y=1;y<o;y++){a[1].unshift(a[1].pop());a[2].push(a[2].shift());a[2].push(a[2].shift());a[3].push(a[3].shift());for(var z=0;z<4;z++){for(var i=0;i<4;i++){a[z][i]=j[a[z][i]]}}for(var z=0;z<4;z++){for(var i=0;i<4;i++){a[z][i]^=m[(o-y)*4+i][z]}}for(var i=0;i<4;i++){var x=a[0][i],w=a[1][i],v=a[2][i],u=a[3][i];a[0][i]=f[x]^n[w]^h[v]^q[u];a[1][i]=q[x]^f[w]^n[v]^h[u];a[2][i]=h[x]^q[w]^f[v]^n[u];a[3][i]=n[x]^h[w]^q[v]^f[u]}}a[1].unshift(a[1].pop());a[2].push(a[2].shift());a[2].push(a[2].shift());a[3].push(a[3].shift());for(var z=0;z<4;z++){for(var i=0;i<4;i++){a[z][i]=j[a[z][i]]}}for(var z=0;z<4;z++){for(var i=0;i<4;i++){a[z][i]^=m[i][z]}}for(var z=0;z<l._blocksize;z++){for(var i=0;i<4;i++){t[s+i*4+z]=a[z][i]}}},_init:function(i){r=i.length/4;o=r+6;l._keyexpansion(i)},_keyexpansion:function(s){m=[];for(var t=0;t<r;t++){m[t]=[s[t*4],s[t*4+1],s[t*4+2],s[t*4+3]]}for(var t=r;t<l._blocksize*(o+1);t++){var i=[m[t-1][0],m[t-1][1],m[t-1][2],m[t-1][3]];if(t%r==0){i.push(i.shift());i[0]=e[i[0]];i[1]=e[i[1]];i[2]=e[i[2]];i[3]=e[i[3]];i[0]^=d[t/r]}else{if(r>6&&t%r==4){i[0]=e[i[0]];i[1]=e[i[1]];i[2]=e[i[2]];i[3]=e[i[3]]}}m[t]=[m[t-r][0]^i[0],m[t-r][1]^i[1],m[t-r][2]^i[2],m[t-r][3]^i[3]]}}}})();