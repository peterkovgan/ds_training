package com.company.string;

import java.util.Arrays;
import java.util.Comparator;

public class LongestChain {

    public static void main(String[] args) {
        String [] candidates = {"kwigbkfeqp","tpiufntqzo","blmwegckaplqwjpo","uiesdrhcbvbbk","przuvuo","kntmjgnqbxlwh","glac","uz","qqhw","gdtunmaw","neymepxl","eqtxh","qz","bek","xgadqztq","cicgtxs","grakdthb","kwigbkfeqop","uqyqhjqwegizcx","kewiygbkfejqop","tufntz","gulamac","sluiobdm","ujdyugagn","fuyz","eth","vzxiobwgyhrdkh","ikfjaivyvreql","jimpyin","cuotmvfqzizu","tkopssxh","gmwmzsowjf","nel","zo","kntmmjgnqbxtlwh","cyuicglbdtxwsu","vjrenjwntklm","uevrxuklobce","tkaopssxh","rwkedydnadd","uqyqhjqdwegizcx","ouethhse","zcmmrtilpti","tmfuxxyk","honyw","nyepl","qqhqwix","mjhg","gdtwunmaw","kigbkfeqp","wnyhsm","oeqatxhree","qyqhjqweizcx","xadqztq","wqnyghsmt","ndozeaylmetnpxlj","przuvuhob","wqnlyghnqsmtt","tkossxh","qyqhjqwix","epwcjxytvt","bexk","ga","em","zojodbw","gulmac","cizocgw","suibdm","nvcyutywhus","bwjofkuchx","f","pdtwv","dzohajcodbw","kuc","tpawplvfzlx","mg","re","oh","wciqob","el","wciqo","mftmgv","grakkdthtbd","cnbnyvuhmrj","odqphbhkf","oeqatxhre","sqzrlhs","bwsjofvkuptchx","xdqztq","tmv","kewigbkfejqop","faepomn","bdwwnalubrvxtdgu","cotmfqziz","lvzl","dmqz","hdmqz","gdigs","ujdyugag","mepjqbuvdku","g","kigbkeqp","oegqataxhree","zscinzobcgtwdwhn","fuaepwobimn","qf","cusotmvfqziknzu","qvroffuk","rweydnadd","zoow","meqcfzkbnrb","tpaplvzlx","zscinzocgwdwhn","zcmmrtilpt","ndeaymenpxlj","jimpn","wofkuchx","tkaopsssxh","vodwqphbhkfx","faepwoimn","gzenkasrciui","nyhm","tpufntqzo","ovqeyvxfdll","tufntzo","gmyrmf","vxobwgrdkh","faepwobimn","przujvuhobk","vzxobwgyrdkh","ksszdumgko","kewiylgbkfejqop","rey","vo","qqfe","moyddffrwwyzk","qzwsbnuejvsi","vodwqpzhbhjkfx","r","vttpoelti","sqzrlehhs","tpiuftntqzo","qfe","qyvsrofgnfuk","evxuklce","wiqo","prpzuujvuaxhkobk","gy","vnjxkjvmz","qroffuk","qqhwi","przuvuhobk","mfttmgvj","moydfrwzk","jibmpuyin","pwcytvt","eqtxhre","gcdtwunmaw","wfkucx","ryedhy","vttpolti","qyqhqwix","kutkavldvvqvp","el","tmfuxxyku","tplvzlx","gdtu","z","ksg","xmgadqzptq","qcqfre","zcmmrtiklpti","hobxltexk","vburq","funyz","kc","vjrenjwntklqm","wqnlyghnqsmt","sibdm","vodwqpzhbhkfx","zsnv","pouegzfyk","arci","vzxobwgyhrdkh","fetoesaot","riyedhy","cyuicgtxsu","gdtua","gchdtwgwunmaw","cuotmvfqziz","obltexk","epwcytvt","scinzocgw","ptwv","jimpuyin","uevrxukloce","tkqaopsssxh","pdtwdv","fetsaot","cyuicgldtxsu","przujvuaxhkobk","wciqoxb","gdtuna","lvzlx","ytpawcplvfzlx","uyugag","ksszdumugiko","bm","pgdmigs","grakdthtbd","uyz","zscinzobcgwdwhn","oeqataxhree","qzsbnuvs","gznkarciui","zoodw","jimpin","tqksugh","zuvo","tmgv","gdmigs","sluibdm","nvcxyzwyutywhus","bwsjofkuchx","fetsat","kgbeqp","neymenpxlj","vzxobwgrdkh","tpawcplvfzlx","qbzwsbnuejvsi","przuvuho","wqnlyguhnqsmtt","ujhadylujgaazgsn","axuelrnnnwhg","qbzwsbnuejvsmoi","qzsbnuvsi","yugag","bwsjofvkupchx","mqz","gchdtwunmaw","kntmmjgnqbxlwh","xfpt","vvburq","moydfrwk","qksgh","faepoimn","odwqphbhkfx","ryey","gchdtwwunmaw","uiesdrhcvbbk","qyqhjqwicx","yilxbltwwxh","jrenjwntkm","gzenkarciui","moyddffrhwwyezk","tqksugvhr","oveyvxfl","agmyrmf","scinzocgwdn","neymenpxl","gonpxzngjv","rwkeydnadd","zohjodbw","bdwwnalubvxtdgu","jrenjwntk","ovqheyvxubfdtll","qcqfqrezof","znkarcii","b","qzsnuvs","ndzeaylmenpxlj","tossxh","fetsa","przujvuxhkobk","ovqeyvxubfdll","tpufntzo","cuicgtxs","mv","vbur","moyddffrhwwyzk","yilxcbltwwxh","eqtxhe","zow","qbzwsbnuejvsoi","nvczwyutywhus","cyquicglbdtxwsu","evxukce","axuelrnnwhg","aci","ouemthhqse","wfkuchx","bwsjofvkmuptchx","mfjhg","bwsjofvkuchx","qffu","qyqhjqwegizcx","rzuvuo","uvo","qh","qvsrofgfuk","neymepl","mepjqybuvdku","sqzrlehs","cnbnyvumrj","qcqfqrezoif","gyrm","przujvuhkobk","cuotmvfqzinzu","gym","ujdylujgaagn","thdmmqz","honw","odwqphbhkf","ksszgko","qbzwsbnuejvsmvoi","m","gtu","qyvsrofgfuk","uqqyqhjqdwegizcx","obtexk","hobltexk","ftmgv","bwsjofvkmuptchxy","taynfxo","tqksugvhrc","jibmpduyin","vxue","zuvuo","gonpxzngv","gmpwamzsowjf","rwednadd","zv","tosh","pqdtwdv","zcmmrtsiklpti","tkqaopesssxh","ovqeyvxbfdll","gac","gsqzrlehhs","znv","cuotmvfqziknzu","ryedy","vnjoxkjvmz","pouegnzfyk","evrxuklce","ujdyujgagn","tmfnuxxyku","semj","xmgadvqzptq","cnnyvmrj","gonpxzvngjv","meqczkbnrb","bwofkuchx","fuvnyz","znarci","moydffrwzk","kwigbkfejqop","wqnyghnsmt","uiesdrehcbvobbk","qcqfreo","zohajodbw","nvcxyzwyutywhuhs","scinzocgwdwn","ksyszdumugiko","qfu","mjg","zsnuvs","eqth","ccgtxs","pgdmidtgs","rwednad","lel","cusotmvfqzwiknzu","znkarciui","o","qzsbnuevsi","veodwqpzhbhjkfx","jrenjwntklm","gmwamzsowjf","ndeymenpxlj","moydffrwwzk","nvczyutywhus","dzohajodbw","ujadylujgaazgsn","nvcytywhus","qcqfrezo","mfttmgv","tqksugvh","qksg","njxkjvmz","udyugag","qffuk","ksszdugko","wkuc","tmfxxyk","qqh","sbdm","rwedna","ovqheyvxubsfdtll","ndzeaymenpxlj","neyepl","qf","xgadqzptq","redna","pougzfyk","zarci","mftbtmgvj","taplvzlx","uiesdrhcbvobbk","ovqeyvxfdl","qcqfrezof","qvrofgfuk","scizocgw","mftbtmgvdj","rsgwmsted","ovqheyvxubfdll","oveyvxfdl","m","wi","znkarci","ikfjaivyvrql","pgdmidgs","btexk","cuotmfqziz","xdqzq","jimp","qqhqwi","lvz","ilojawyersju","wnyhm","gsqzrlenhhs","bu","wkucx","bdm","lsel","tpawplvzlx","ai","kntmmjognqbxtlwh","hkonyw","vxuce","zsnvs","qyqhjqweicx","nyel","cyuicgtxs","qzsbnuejvsi","ujadylujgaagsn","thdmqz","ilojawyersu","uevirxuklobce","qrffuk","zoodbw","sem","cyuicgldtxwsu","wnyhsmt","prpzujvuaxhkobk","uesdrhcvbbk","vhvburq","ndzeaylmetnpxlj","ksszdgko","scinzocgwd","cyuicgltxsu","ptw","mplaxm","scinzocgwdwhn","ytpawcplvifzlx","evxuce","bur","kigbeqp","ksszdumugko","cnnyvumrj","toh","kewiylgbkfejqfop","gulac","pugzfyk","nvcyzwyutywhus","ouemthhse","wqnyghnqsmt","b","ujdylujgagn","tlvzlx","pgidmidtgs","eqatxhre","gmyrm","dqzq","oveyvxl","vttpoeltci","gk","wqnyhsmt","zcmmrtsitklpti","iaxuelrnnnwhg","gdtunma","ujadylujgaagn","tqksgh","moydffrwwyzk","mfftbtmgvdj","wio","fetosaot","grakdthtb","nxmrvm","evrxukloce","ikfjaivyrql","tosxh","grakkbdthtbd","epwcxytvt","blmwegckaplwjpo","qcqfe"};
        System.out.println(longestStrChain(candidates));
    }

    public static int longestStrChain(String[] candidates) {

        if(candidates==null) return 0;
        if(candidates.length < 1) return 0;
        if(candidates.length == 1) return 1;

        Comparator<String> comparator  = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        };
        Arrays.sort(candidates, comparator);

        int longest = 1;

        for(int y = 0; y < candidates.length - 1; y++){

            String first =  candidates[y];

            String previous = null;

            int chainLength = 1;

            for(int x = y + 1; x < candidates.length; x++){

                if(previous==null){
                    previous = first;
                }

                String next = candidates[x];

                if( chained(previous, next) ){
                    chainLength++;
                    previous = next;
                }else{
                    if(chainLength > longest){
                        longest = chainLength;
                    }
                    //previous = next;
                    //chainLength = 1;
                }
            }
            if(chainLength > longest){
                longest = chainLength;
            }

        }

        //Arrays.stream(candidates).forEach(System.out::println);

        return longest;
    }

    private static boolean chained(String previous, String next) {

        char [] nextChars = next.toCharArray();

        char [] prevChars = previous.toCharArray();


        if(nextChars.length - prevChars.length > 1) return false;


        int matches = 0;

        int count = 0;

        for(int i = 0, y=0; i < nextChars.length && y < prevChars.length;){
            if(nextChars[i] == prevChars[y]){
                matches++;
                i++; y++;
            }else if(count==0){
                i++;
                count++;
            }else if(count > 0){
                return false;
            }
        }


        boolean chained =  (matches==prevChars.length && count <= 1)?true:false;

//        if(chained) {
//            System.out.println("chained " + chained + " " + previous + " " + next);
//        }
        return chained;
    }
}
