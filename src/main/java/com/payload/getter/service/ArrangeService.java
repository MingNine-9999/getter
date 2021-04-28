package com.payload.getter.service;

import org.springframework.stereotype.Service;

@Service
public class ArrangeService {

    public static String arrangeString(String body) {
        StringBuilder builder = new StringBuilder();

        int indent = 0;
        boolean check = true;
        for (int i = 0; i < body.length(); i++) {
            if (check) {
                switch (body.charAt(i)) {
                    case '{':
                    case '[':
                        indent++;

                        builder.append(body.charAt(i));

                        if (!(body.charAt(i) == '[' && body.charAt(i + 1) == ']') && !(body.charAt(i) == '{' && body.charAt(i + 1) == '}')) {
                            builder.append('\n');
                            for (int j = 0; j < indent * 2; j++) {
                                builder.append(' ');
                            }
                        }
                        break;
                    case '}':
                    case ']':
                        indent--;

                        if (!(body.charAt(i - 1) == '[' && body.charAt(i) == ']') && !(body.charAt(i - 1) == '{' && body.charAt(i) == '}')) {
                            builder.append('\n');
                            for (int j = 0; j < indent * 2; j++) {
                                builder.append(' ');
                            }
                        }
                        builder.append(body.charAt(i));

                        if (i + 1 < body.length() && (body.charAt(i + 1) != ',' && body.charAt(i + 1) != ']' && body.charAt(i + 1) != '}')) {
                            builder.append('\n');
                            for (int j = 0; j < indent * 2; j++) {
                                builder.append(' ');
                            }
                        }
                        break;
                    case ',':
                        builder.append(body.charAt(i));
                        builder.append('\n');
                        for (int j = 0; j < indent * 2; j++) {
                            builder.append(' ');
                        }
                        break;
                    case ':':
                        builder.append(body.charAt(i));
                        builder.append(' ');
                        break;
                    case '\"':
                        builder.append(body.charAt(i));
                        check = false;
                        break;
                    default:
                        builder.append(body.charAt(i));
                }
            } else {
                builder.append(body.charAt(i));
                if (body.charAt(i) == '\"') {
                    check = true;
                }
            }
        }

        return builder.toString();
    }
}
