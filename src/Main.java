import java.util.Scanner;

public class Main {


    enum state {q0, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, q15, q16}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size1 = 100000000;
        int size2 = 100000000;
        int size3 = 100000000;
        int head1 = size1 / 2;
        int head2 = size2 / 2;
        int head3 = size3 / 2;

        char[] tape1 = new char[size1];
        char[] tape2 = new char[size2];
        char[] tape3 = new char[size3];

        initialize_tape(tape1 , size1);
        initialize_tape(tape2,size2);
        initialize_tape(tape3,size3);

        System.out.println("Please enter your desired number : ");
        int n = scanner.nextInt();

        //multiplication part : 4 * n
        initialize_mul(tape1, size1, head1, 4, n);
        multiplication(head1, tape1);

        //addition part : 4n + 2
        head1 = resetHead(tape1, size1);
        initialize_add(tape1, size1, head1, 2);
        head1 = resetHead(tape1, size1);
        addition(head1, tape1);

        // factorial part : (4n + 2)!
        head1 = resetHead(tape1, size1);
        initialize_fact(tape1,size1,head1);
        factorial(tape1,tape2,tape3,head1,head2,head3);

//        System.out.println(tape1);
        System.out.println( "The output : "+output(tape1, size1));


    }

    /**
     * put b in every index of tape
     * @param tape : the tape
     * @param size : size of tape
     */
    public static void initialize_tape(char[] tape , int size){
        for (int i = 0; i < size; i++) {
            tape[i] = 'b';
        }
    }

    /**
     * put a '1' in the first blank position after 0s
     * @param tape
     * @param size
     * @param head
     */
    public static void initialize_fact(char[] tape,int size , int head){
        for (int i = 0; i < size; i++) {
            if (tape[head + i] == '0' && tape[head+i+1] == 'b'){
                tape[head + i + 1] = '1';
                return;
            }
        }
    }

    /**
     * do the factorial operation based on the table mentioned in the report
     * @param tape1
     * @param tape2
     * @param tape3
     * @param head1
     * @param head2
     * @param head3
     */
    public static void factorial(char[] tape1, char[] tape2, char[] tape3, int head1, int head2, int head3) {
        head2 = head1;
        head3 = head1;
        state current_state = state.q0;
        boolean isDone = false;
        while (!isDone) {
            if (current_state == state.q0) {
                if (tape1[head1] == '0' && tape2[head2] == 'b' && tape3[head3] == 'b') {
                    tape2[head2] = '0';
                    head1++;
                    head2++;
                    current_state = state.q0;
                } else if (tape1[head1] == '1' && tape2[head2] == 'b' && tape3[head3] == 'b') {
                    tape1[head1] = 'b';
                    head1--;
                    head2--;
                    current_state = state.q1;
                }
            } else if (current_state == state.q1) {
                if (tape1[head1] == '0' && tape2[head2] == '0' && tape3[head3] == 'b') {
                    tape2[head2] = 'b';
                    head2--;
                    head3++;
                    current_state = state.q2;
                } else if (tape1[head1] == 'b' && tape2[head2] == 'b' && tape3[head3] == 'b') {
                    tape1[head1] = '0';
                    current_state = state.q2;
                }
            } else if (current_state == state.q2) {
                if (tape1[head1] == '0' && tape2[head2] == '0' && tape3[head3] == 'b') {
                    tape3[head3] = '0';
                    head1--;
                    head3++;
                    current_state = state.q2;
                } else if (tape1[head1] == '0' && tape2[head2] == '0' && tape3[head3] == '0') {
                    head1--;
                    head3++;
                    current_state = state.q2;
                }else if (tape1[head1] == 'b' && tape2[head2] == '0' && tape3[head3] == 'b') {
                    head1++;
                    head2--;
                    current_state = state.q3;
                } else if (tape1[head1] == '0' && tape2[head2] == 'b' && tape3[head3] == 'b') {
                    head2++;
                    current_state = state.q7;
                }
            }  else if (current_state == state.q3) {
                if (tape1[head1] == '0' && tape2[head2] == '0' && tape3[head3] == 'b') {
                    tape3[head3] = '0';
                    head1++;
                    head3++;
                    current_state = state.q3;
                } else if (tape1[head1] == '0' && tape2[head2] == '0' && tape3[head3] == '0') {
                    head1++;
                    head3++;
                    current_state = state.q3;
                }else if (tape1[head1] == 'b' && tape2[head2] == '0' && tape3[head3] == 'b') {
                    head1--;
                    head2--;
                    current_state = state.q2;
                } else if (tape1[head1] == '0' && tape2[head2] == 'b' && tape3[head3] == 'b') {
                    head2++;
                    current_state = state.q4;
                }
            } else if (current_state == state.q4) {
                if (tape1[head1] == '0' && tape2[head2] == '0' && tape3[head3] == 'b') {
                    tape2[head2] = 'b';
                    head2++;
                    head3--;
                    current_state = state.q5;
                }
            } else if (current_state == state.q5) {
                if (tape1[head1] == '0' && tape2[head2] == '0' && tape3[head3] == '0') {
                    head1++;
                    head3--;
                    current_state = state.q5;
                } else if (tape1[head1] == 'b' && tape2[head2] == '0' && tape3[head3] == '0') {
                    tape1[head1]= '0';
                    head1++;
                    head3--;
                    current_state = state.q5;
                }else if (tape1[head1] == 'b' && tape2[head2] == '0' && tape3[head3] == 'b') {
                    head1--;
                    head2++;
                    current_state = state.q6;
                } else if (tape1[head1] == '0' && tape2[head2] == 'b' && tape3[head3] == '0') {
                    current_state = state.q10;
                }
            } else if (current_state == state.q6) {
                if (tape1[head1] == '0' && tape2[head2] == '0' && tape3[head3] == 'b') {
                    head2++;
                    current_state = state.q6;
                } else if (tape1[head1] == '0' && tape2[head2] == 'b' && tape3[head3] == 'b') {
                    head2--;
                    head3++;
                    current_state = state.q2;
                }
            } else if (current_state == state.q7) {
                if (tape1[head1] == '0' && tape2[head2] == '0' && tape3[head3] == 'b') {
                    tape2[head2] = 'b';
                    head2++;
                    head3--;
                    current_state = state.q8;
                }
            } else if (current_state == state.q8) {
                if (tape1[head1] == '0' && tape2[head2] == '0' && tape3[head3] == '0') {
                    head1--;
                    head3--;
                    current_state = state.q8;
                } else if (tape1[head1] == 'b' && tape2[head2] == '0' && tape3[head3] == '0') {
                    tape1[head1]='0';
                    head1--;
                    head3--;
                    current_state = state.q8;
                }else if (tape1[head1] == 'b' && tape2[head2] == '0' && tape3[head3] == 'b') {
                    head1++;
                    head2++;
                    current_state = state.q9;
                } else if (tape1[head1] == '0' && tape2[head2] == 'b' && tape3[head3] == '0') {
                    current_state = state.q10;
                }
            } else if (current_state == state.q9) {
                if (tape1[head1] == '0' && tape2[head2] == '0' && tape3[head3] == 'b') {
                    head2++;
                    current_state = state.q9;
                } else if (tape1[head1] == '0' && tape2[head2] == 'b' && tape3[head3] == 'b') {
                    head2--;
                    head3++;
                    current_state = state.q3;
                }
            } else if (current_state == state.q10) {
                isDone = true;
            }
        }
    }

    /**
     * show first 0's position in the tape
     * @param tape
     * @param size
     * @return
     */
    public static int resetHead(char[] tape, int size) {
        for (int i = 0; i < size; i++) {
            if (tape[i] == '0')
                return i;
        }
        return -1;
    }


    /**
     * count the number of 0s in the tape and return that
     * @param tape
     * @param size
     * @return the number of 0s
     */
    public static int output(char[] tape, int size) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (tape[i] == '0')
                count++;
        }
        return count;
    }

    /**
     * put the number n and 4 in the tape in format : bbb0(n times)b0000bbbb
     * @param tape : the tape
     * @param size : size of tape
     * @param head : pointer to tape
     * @param m : operand
     * @param n : operand
     */
    public static void initialize_mul(char[] tape, int size, int head, int m, int n) {
        for (int i = 0; i < m; i++) {
            tape[head + i] = '0';
        }
        for (int i = 0; i < n; i++) {
            tape[head + m + i + 1] = '0';
        }


    }

    /**
     * put two 0s before the other 0s
     * @param tape
     * @param size
     * @param head
     * @param m : same the 2
     */
    public static void initialize_add(char[] tape, int size, int head, int m) {
        for (int i = 1; i <= m; i++) {
            tape[head - i - 1] = '0';
        }
    }


    /**
     * do the addition operation based on the table in report
     * @param head
     * @param tape
     */
    public static void addition(int head, char[] tape) {
        state current_state = state.q0;
        boolean isDone = false;
        while (!isDone) {
            if (current_state == state.q0) {
                if (tape[head] == 'b') {
                    head++;
                    current_state = state.q0;
                } else if (tape[head] == '0') {
                    head++;
                    current_state = state.q1;
                }
            } else if (current_state == state.q1) {
                if (tape[head] == '0') {
                    head++;
                    current_state = state.q1;
                } else if (tape[head] == 'b') {
                    tape[head] = '0';
                    head++;
                    current_state = state.q2;
                }
            } else if (current_state == state.q2) {
                if (tape[head] == 'b') {
                    head--;
                    current_state = state.q3;
                } else if (tape[head] == '0') {
                    head++;
                    current_state = state.q2;
                }
            } else if (current_state == state.q3) {
                if (tape[head] == 'b') {
                    head--;
                    current_state = state.q3;
                } else if (tape[head] == '0') {
                    tape[head] = 'b';
                    head--;
                    current_state = state.q4;
                }
            } else if (current_state == state.q4) {
                if (tape[head] == 'b') {
                    isDone = true;
                } else if (tape[head] == '0') {
                    head--;
                    current_state = state.q4;
                }
            }
        }
    }

    /**
     * doing the operation * between two numbers
     * @param head : pointer to tape
     * @param tape : the tape
     */
    public static void multiplication(int head, char[] tape) {
        state current_state = state.q0;
        boolean isDone = false;
        while (!isDone) {
            if (current_state == state.q0) {
                if (tape[head] == 'b') {
                    head++;
                    current_state = state.q1;
                } else if (tape[head] == '0') {
                    tape[head] = 'b';
                    head++;
                    current_state = state.q2;
                }
            } else if (current_state == state.q1) {
                if (tape[head] == 'b') {
                    head++;
                    current_state = state.q14;
                } else if (tape[head] == '0') {
                    tape[head] = 'b';
                    head++;
                    current_state = state.q2;
                }
            } else if (current_state == state.q2) {
                if (tape[head] == 'b') {
                    head++;
                    current_state = state.q3;
                } else if (tape[head] == '0') {
                    head++;
                    current_state = state.q2;
                }
            } else if (current_state == state.q3) {
                if (tape[head] == 'b') {
                    head--;
                    current_state = state.q15;
                } else if (tape[head] == '0') {
                    tape[head] = 'b';
                    head++;
                    current_state = state.q4;
                }
            } else if (current_state == state.q4) {
                if (tape[head] == 'b') {
                    head++;
                    current_state = state.q5;
                } else if (tape[head] == '0') {
                    head++;
                    current_state = state.q4;
                }
            } else if (current_state == state.q5) {
                if (tape[head] == '0') {
                    head++;
                    current_state = state.q5;
                } else if (tape[head] == 'b') {
                    tape[head] = '0';
                    head--;
                    current_state = state.q6;
                }
            } else if (current_state == state.q6) {
                if (tape[head] == 'b') {
                    head--;
                    current_state = state.q7;
                } else if (tape[head] == '0') {
                    head--;
                    current_state = state.q6;
                }
            } else if (current_state == state.q7) {
                if (tape[head] == '0') {
                    head--;
                    current_state = state.q8;
                } else if (tape[head] == 'b') {
                    tape[head] = '0';
                    head--;
                    current_state = state.q9;
                }
            } else if (current_state == state.q8) {
                if (tape[head] == '0') {
                    head--;
                    current_state = state.q8;
                } else if (tape[head] == 'b') {
                    tape[head] = '0';
                    head++;
                    current_state = state.q3;
                }
            } else if (current_state == state.q9) {
                if (tape[head] == 'b') {
                    head--;
                    current_state = state.q10;
                } else if (tape[head] == '0') {
                    head--;
                    current_state = state.q9;
                }
            } else if (current_state == state.q10) {
                if (tape[head] == 'b') {
                    head++;
                    current_state = state.q12;
                } else if (tape[head] == '0') {
                    head--;
                    current_state = state.q11;
                }
            } else if (current_state == state.q11) {
                if (tape[head] == 'b') {
                    head++;
                    current_state = state.q0;
                } else if (tape[head] == '0') {
                    head--;
                    current_state = state.q11;
                }
            } else if (current_state == state.q12) {
                if (tape[head] == 'b') {
                    head++;
                    current_state = state.q12;
                } else if (tape[head] == '0') {
                    tape[head] = 'b';
                    head++;
                    current_state = state.q13;
                }
            } else if (current_state == state.q13) {
                if (tape[head] == 'b') {
                    isDone = true;
                } else if (tape[head] == '0') {
                    tape[head] = 'b';
                    head++;
                    current_state = state.q13;
                }
            } else if (current_state == state.q14) {
                if (tape[head] == 'b') {
                    isDone = true;
                } else if (tape[head] == '0') {
                    tape[head] = 'b';
                    head++;
                    current_state = state.q14;
                }
            } else if (current_state == state.q15) {
                if (tape[head] == 'b') {
                    head--;
                    current_state = state.q16;
                } else if (tape[head] == '0') {
                    tape[head] = 'b';
                    head--;
                    current_state = state.q15;
                }
            } else if (current_state == state.q16) {
                if (tape[head] == 'b') {
                    isDone = true;
                } else if (tape[head] == '0') {
                    tape[head] = 'b';
                    head--;
                    current_state = state.q16;
                }
            }
        }
    }
}
