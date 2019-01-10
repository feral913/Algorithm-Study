/*
 * 다음과 같은 암호 알고리즘을 이용해서 원문 plain_text를 암호화해서 encrypted_text를 만들었습니다.
 * 1. 암호화시킬 문장 plain_text와 같은 길이의 문자열 key를 준비합니다.
 * 2. 암호화시킬 문장을 key를 이용해서 암호화시킵니다.
 * 3. 2번 결과로 나온 문장을 rotation 만큼 회전시켜 줍니다.
 * 예를 들어서 암호화시킬 문장이 hellopython 이고, key가 abcdefghijk, rotation이 3이라고 하겠습니다.
 * 먼저 암호화시킬 문장과 key를 이용해 다음과 같이 암호화해줍니다.
 * - key에 있는 소문자 'a' ~ 'z' 는 각각 순서대로 1~26 까지의 숫자를 의미합니다.
 * - plain_text의 각 알파벳을 key의 대응되는 위치에 있는 소문자가 나타내는 숫자만큼 뒤쪽에 나타나는 알파벳으로 바꿉니다.
 *  - 예를 들어, plain_text의 'e'에 대응되는 key의 알파벳이 'b'라면, 'e'에서 2만큼 뒤에 있는 알파벳 'g'로 바꾸면 됩니다.
 * - 이때 'z'를 넘어가는 문자는 다시 'a'부터 시작합니다. (xyz을 dbc로 암호화시키면 결과는 bac입니다)
 * 위 방식대로 hellopython을 abcdefghijk을 이용해 암호화시키면 다음과 같이 igoptvfbqyy로 암호화됩니다.
 *  'h' + 'a' = 'i' ('h'에서 1만큼 뒤에 있는 알파벳은 'i')
 *  'e' + 'b' = 'g' ('e'에서 2만큼 뒤에 있는 알파벳은 'g')
 *  ...
 *  'y' + 'g' = 'f' ('y'에서 7만큼 뒤에 있는 알파벳은 'f', 'z'를 넘어가므로 다시 'a' 부터 시작)
 *  ...
 *  'n' + 'k' = 'y' ('n'에서 11만큼 뒤에 있는 알파벳은 'y')
 * 문자를 바꾼 후에는 다음과 같이 rotaion의 수치만큼 문자열을 회전시켜 줍니다. rotation값이 양수면 오른쪽으로, 음수인 경우는 왼쪽으로 회전을 시켜 줍니다.
 *  0 : igoptvfbqyy
 *  1 : yigoptvfbqy
 *  2 : yyigoptvfbq
 *  3 : qyyigoptvfb
 * 위와 같은 알고리즘으로 암호화된 문장 encrypted_text, 암호화에 사용된 key, 와rotation이 매개변수로 주어질 때, 암호화를 하기 이전의 문장을 구해 return 하는 solution 함수를 완성해주세요.
 * 암호화된 문장 encrypted_text 의 길이는 1 이상 1,000 이하입니다.
 * 암호화된 문장 encrypted_text와 암호화되기 전 문장은 알파벳 소문자로만 구성되어 있습니다.
 * 암호화에 사용되는 문장 key의 길이는 encrypted_text의 길이와 같으며, 알파벳 소문자로만 구성되어 있습니다.
 * 회전횟수 rotation은 -1,000 이상 1,000 이하의 정수입니다.
 */
package test.nts20190105.nts3;

// rotation 만큼 암호화된 문자열의 문자를 왼쪽으로 한칸씩 밀어준다.
// rotation이 음수이면 오른쪽으로 밀어준다.
// key 문자열의 각 문자에서 96을 빼고 그 값을 다시 암호화된 문자에서 뺴준다.
// 만약 뺀 값이 97(a)보다 작아지면 26을 더해서 알파벳의 뒤에서부터 맞는 자리를 찾아준다.
class Solution {
    public String solution(String encrypted_text, String key, int rotation) {
        String answer = "";
        int strLength = encrypted_text.length();
        char[] etArr = new char[strLength];
        char[] keyArr = new char[strLength];
        char[] etArr2 = new char[strLength];
        
        for(int i = 0; i < strLength; i++){
            etArr[i] = encrypted_text.charAt(i);
            keyArr[i] = key.charAt(i);
        }
        
        etArr2 = strShift(etArr, rotation).clone();
        answer = String.valueOf(strMinus(etArr2, keyArr));
        
        return answer;
    }
    
    public char[] strShift(char[] etArr, int rot){
        if(rot >= 0){
            for(int i = 0; i < rot; i++){
                for(int j = 0; j < etArr.length - 1; j++){
                    char temp = etArr[j];
                    etArr[j] = etArr[j + 1];
                    etArr[j + 1] = temp;
                }
            }
        } else{
            rot = -rot;
            for(int i = 0; i < rot; i++){
                for(int j = etArr.length - 1; j > 0; j--){
                    char temp = etArr[j];
                    etArr[j] = etArr[j - 1];
                    etArr[j - 1] = temp;
                }
            }
        }
        
        return etArr;
    }
    
    public char[] strMinus(char[] etArr, char[] keyArr){
        for(int i = 0; i < etArr.length; i++){
            int minus = keyArr[i] - 96;
            if(etArr[i] - minus < 97){
                etArr[i] = (char)(etArr[i] - (keyArr[i] - 96) + 26);
            } else{
                etArr[i] = (char)(etArr[i] - (keyArr[i] - 96));
            }
        }
        
        return etArr;
    }
}

public class NTS3 {

	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.solution("qyyigoptvfb", "abcdefghijk", 3));
	}

}
