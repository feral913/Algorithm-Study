/*
 * 입력받은 숫자를 우리가 읽는 소리로 출력하는 코드를 작성하시오.
 * 입력값은 1 ~ 99999의 정수에 한함.
 */
package test.nts20190105.nts2;

// 숫자 범위가 제한되어 있기 때문에 switch 문을 이용해서 하나하나 대입해주었다.
// 0은 읽을 필요 없기 때문에 1 ~ 9를 확인하고 변환해준다.
// 자릿수에 대해서는 숫자 배열의 인덱스에 맞춰서 십 ~ 만의 자릿수 소리를 대입해준다.
// 십 ~ 만의 자릿수에 숫자가 1인 경우 일은 대입하지 않는다.
// 자릿수와 숫자 소리를 별도의 함수를 이용해서 대입하는 과정에서 0으로 해당 소리가 필요 없는 경우 초기값을 반환하게 되는데(처음에는 char ret = 0;으로 설정했었다.)
// 초기값이 최종 결과값에 포함되는데 그걸 인지하지 못해서 반환 값이 맞는데 정답으로 인식이 안된다고 헤맸었다.
// 뒤늦게 원인을 인지하고 if문에서 걸러주었다. 시간을 많이 소비했다.
class Solution {
    public String solution(int num) {
        String answer = "";
        String numStr = String.valueOf(num);

        int length = (int)(Math.log10(num)+1);
        int[] numArr = new int[length];
        
        for(int i = 0; i < length; i++){
            numArr[i] = numStr.charAt(i) - '0';
        }
        
        for(int i = 0; i < length; i++){
            if(readNum(numArr[i]) == '일' && length - i != 1);
            else if(readNum(numArr[i]) != '\u0000') answer += readNum(numArr[i]);

            if(readNum(numArr[i]) != '\u0000' && readCipher(length - i) != '\u0000'){
                answer += readCipher(length - i);
            }
        }
        
        return answer;
    }
    
    public char readCipher(int i){
        char ret = '\u0000';
        
        switch(i){
            case 1: 
                break;
            case 2: ret = '십';
                break;
            case 3: ret = '백';
                break;
            case 4: ret = '천';
                break;
            case 5: ret = '만';
                break;
        }
        
        return ret;
    }
    
    public char readNum(int num){
        char ret = '\u0000';
        
        switch(num){
            case 1: ret = '일';
                break;
            case 2: ret = '이';
                break;
            case 3: ret = '삼';
                break;
            case 4: ret = '사';
                break;
            case 5: ret = '오';
                break;
            case 6: ret = '육';
                break;
            case 7: ret = '칠';
                break;
            case 8: ret = '팔';
                break;
            case 9: ret = '구';
                break;
        }
        
        return ret;
    }
}

public class NTS2 {

	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.solution(2001));
		System.out.println(sol.solution(98030));
		System.out.println(sol.solution(11010));
	}

}
