package dropwizard.springboot.dropwizardToSpringboot.utility;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class IdempotencyResponseWrapper extends HttpServletResponseWrapper {
    private final ByteArrayOutputStream capture;
    private final ServletOutputStream output;
    private final PrintWriter writer;

    public IdempotencyResponseWrapper(HttpServletResponse response) {
        super(response);
        capture = new ByteArrayOutputStream(response.getBufferSize());
        output = new ServletOutputStream() {
            @Override
            public void write(int b) throws IOException {
                capture.write(b);
            }

            @Override
            public void flush() throws IOException {
                capture.flush();
            }

            @Override
            public void close() throws IOException {
                capture.close();
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {
                // No implementation needed
            }
        };
        writer = new PrintWriter(capture);
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return output;
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    public byte[] getDataStream() {
        writer.flush();
        return capture.toByteArray();
    }
}
