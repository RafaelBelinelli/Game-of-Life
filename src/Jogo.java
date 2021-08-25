import java.io.*;

public class Jogo {
    private final int X = 50;
    private final int Y = 50;
    private char[][] matriz = new char[X][Y];
    private boolean[][] proximo = new boolean[50][50];
    private BufferedReader leitor;
    private String nomeArq = "";
    private char[][] matrizRel;
    private final char FUNDO_PADRAO = '-';
    private final char CELULA_PADRAO = 'o';
    private int xRel = 0;
    private int yRel = 0;
    private boolean acompanhar = true;


    public Jogo (String caminhoArquivo) throws Exception {
        if (caminhoArquivo == null) {
            throw new Exception("Caminho do arquivo nulo");
        }

        try {
            int qtdX = 0;
            int qtdY = 0;

            while (qtdY < this.Y) {
                while (qtdX < this.X) {
                    this.matriz[qtdX][qtdY] = FUNDO_PADRAO;
                    this.proximo[qtdX][qtdY] = false;
                    qtdX++;
                }

                qtdX = 0;
                qtdY++;
            }

            this.leitor = new BufferedReader(new FileReader(String.format("c:\\Users\\Rafael\\ideaProjects\\jogodavida\\jogos\\%s.txt", caminhoArquivo)));
            nomeArq = caminhoArquivo;
        } catch (Exception erro) {
            System.out.println("Arquivo não encontrado!");
        }

        lerArquivo();
        inserirMatriz();
    }

    public char[][] getLabirinto() {
        return this.matriz;
    }

    public int getxRel() {
        return xRel;
    }

    public int getyRel() {
        return yRel;
    }

    public void lerArquivo() {
        try {
            String linha;
            do {
                linha = this.leitor.readLine();
                if (linha != null) {
                    if (xRel == 0) {
                        xRel = linha.length();
                    }
                    yRel++;
                }
            }
            while (linha != null);

            matrizRel = new char[xRel][yRel];

            this.leitor = new BufferedReader(new FileReader(String.format("c:\\Users\\Rafael\\ideaProjects\\jogodavida\\jogos\\%s.txt", nomeArq)));

            int qtdX = 0;
            int qtdY = 0;

            while (qtdY < yRel) {
                while (qtdX < xRel) {
                    char lido = (char) leitor.read();

                    if (lido != '\n' && lido != '\r') {
                        matrizRel[qtdX][qtdY] = lido;
                        qtdX++;
                    }
                }

                qtdX = 0;
                qtdY++;
            }
        } catch (Exception erro) {
            System.err.println(erro.getMessage());
        }
    }

    public void inserirMatriz() {
        try {
            Coordenada localInsercao = new Coordenada((this.X - this.xRel)/2, (this.Y - this.yRel)/2); // top left
            Coordenada localFinal = new Coordenada(this.xRel + localInsercao.getX(), this.yRel + localInsercao.getY()); // bottom right

            int qtdX = localInsercao.getX();
            int qtdY = localInsercao.getY();

            int qtdXrel = 0;
            int qtdYrel = 0;

            while (qtdY < localFinal.getY()) {
                while (qtdX < localFinal.getX()) {
                    matriz[qtdX][qtdY] = matrizRel[qtdXrel][qtdYrel];
                    if (matriz[qtdX][qtdY] == CELULA_PADRAO) {
                        proximo[qtdX][qtdY] = true;
                    }

                    qtdX++;
                    qtdXrel++;
                }

                qtdX = localInsercao.getX();
                qtdXrel = 0;

                qtdY++;
                qtdYrel++;
            }
        } catch (Exception erro) {
            System.err.println(erro.getMessage());
        }
    }

    private Coordenada buscarEmCima() {
        int qtdX = 0;
        int qtdY = 0;
        int maisEmCima = this.Y - 1;
        Coordenada retorno = null;

        try {
            while (qtdY < this.Y) {
                while (qtdX < this.X) {
                    if (matriz[qtdX][qtdY] == CELULA_PADRAO) {
                        if (qtdY < maisEmCima) {
                            maisEmCima = qtdY;
                            retorno = new Coordenada(qtdX, qtdY);
                        }

                    }

                    qtdX++;
                }

                qtdX = 0;
                qtdY++;

            }
        } catch (Exception erro) {
            System.err.println(erro.getMessage());
        }

        return retorno;
    }

    private Coordenada buscarDireita() {
        int qtdX = this.X - 1;
        int qtdY = 0;
        int maisDireita = 0;
        Coordenada retorno = null;

        try {
            while (qtdY < this.Y) {
                while (qtdX >= 0) {
                    if (matriz[qtdX][qtdY] == CELULA_PADRAO) {
                        if (qtdX > maisDireita) {
                            maisDireita = qtdX;
                            retorno = new Coordenada(qtdX, qtdY);
                        }
                    }

                    qtdX--;
                }

                qtdX = this.X - 1;
                qtdY++;

            }
        } catch (Exception erro) {
            System.err.println(erro.getMessage());
        }
        return retorno;
    }

    private Coordenada buscarEsquerda() {
        int qtdX = 0;
        int qtdY = 0;
        int maisEsquerda = this.X - 1;
        Coordenada retorno = null;

        try {
            while (qtdY < this.Y) {
                while (qtdX < this.X) {
                    if (matriz[qtdX][qtdY] == CELULA_PADRAO) {
                        if (qtdX < maisEsquerda) {
                            maisEsquerda = qtdX;
                            retorno = new Coordenada(qtdX, qtdY);
                        }
                    }

                    qtdX++;
                }

                qtdX = 0;
                qtdY++;

            }
        } catch (Exception erro) {
            System.err.println(erro.getMessage());
        }

        return retorno;
    }

    private Coordenada buscarEmbaixo() {
        int qtdX = 0;
        int qtdY = this.Y - 1;
        int maisEmbaixo = 0;
        Coordenada retorno = null;

        try {
            while (qtdY >= 0) {
                while (qtdX < this.X) {
                    if (matriz[qtdX][qtdY] == CELULA_PADRAO) {
                        if (maisEmbaixo < qtdY) {
                            maisEmbaixo = qtdY;
                            retorno = new Coordenada(qtdX, qtdY);
                        }
                    }

                    qtdX++;
                }

                qtdX = 0;
                qtdY--;

            }
        } catch (Exception erro) {
            System.err.println(erro.getMessage());
        }
        return retorno;
    }

    public int quantosVizinhos(Coordenada posicao) {
        int vizinhos = 0;

        if (posicao.getX() - 1 > 0) {
            if (matriz[posicao.getX() - 1][posicao.getY()] == CELULA_PADRAO)
                vizinhos++;

            if (posicao.getY() + 1 < 50) {
                if (matriz[posicao.getX() - 1][posicao.getY() + 1] == CELULA_PADRAO)
                    vizinhos++;
            }

            if (posicao.getY() - 1 > 0) {
                if (matriz[posicao.getX() - 1][posicao.getY() - 1] == CELULA_PADRAO)
                    vizinhos ++;
            }
        }

        if (posicao.getX() + 1 < 50) {
            if (posicao.getY() - 1 > 0) {
                if (matriz[posicao.getX() + 1][posicao.getY() - 1] == CELULA_PADRAO)
                    vizinhos++;
            }

            if (matriz[posicao.getX() + 1][posicao.getY()] == CELULA_PADRAO)
                vizinhos++;

            if (posicao.getY() + 1 < 50) {
                if (matriz[posicao.getX() + 1][posicao.getY() + 1] == CELULA_PADRAO)
                    vizinhos++;
            }
        }

        if (posicao.getY() - 1 > 0) {
            if (matriz[posicao.getX()][posicao.getY() - 1] == CELULA_PADRAO)
                vizinhos++;
        }

        if (posicao.getY() + 1 < 50) {
            if (matriz[posicao.getX()][posicao.getY() + 1] == CELULA_PADRAO)
                vizinhos++;
        }

        return vizinhos;
    }

    public void avancarGeracao() {
        int qtdX = 0;
        int qtdY = 0;

        try {
            while (qtdY < this.Y) {
                while (qtdX < this.X) {
                    if (quantosVizinhos(new Coordenada(qtdX, qtdY)) < 2) {
                        proximo[qtdX][qtdY] = false;
                    } else if (quantosVizinhos(new Coordenada(qtdX, qtdY)) > 3) {
                        proximo[qtdX][qtdY] = false;
                    } else if (quantosVizinhos(new Coordenada(qtdX, qtdY)) == 3) {
                        proximo[qtdX][qtdY] = true;
                    }

                    qtdX++;
                }

                qtdX = 0;
                qtdY++;
            }

            qtdX = 0;
            qtdY = 0;

            while (qtdY < this.Y) {
                while (qtdX < this.X) {
                    if (proximo[qtdX][qtdY]) {
                        matriz[qtdX][qtdY] = CELULA_PADRAO;
                    } else {
                        matriz[qtdX][qtdY] = FUNDO_PADRAO;
                    }

                    qtdX++;
                }

                qtdX = 0;
                qtdY++;
            }
        } catch (Exception erro) {
            System.err.println(erro.getMessage());
        }
    }

    @Override
    public String toString() {
        String ret = "";

        if (acompanhar) {
            int qtdX;
            int qtdY;
            int finalX;
            int finalY;

            boolean deuRuim = false;

            if (buscarEsquerda() != null) {
                qtdX = buscarEsquerda().getX() - 1;
                qtdY = buscarEmCima().getY() - 1;
            } else {
                qtdX = 23;
                qtdY = 23;
                deuRuim = true;
            }

            if (buscarDireita() != null) {
                finalX = buscarDireita().getX() + 1;
                finalY = buscarEmbaixo().getY() + 1;
            } else {
                finalX = 25;
                finalY = 25;
                deuRuim = true;
            }

            while (qtdY <= finalY) {
                while (qtdX <= finalX) {
                    ret += this.matriz[qtdX][qtdY];
                    qtdX++;
                }

                ret += '\n';

                if (!deuRuim) {
                    qtdX = buscarEsquerda().getX() - 1;
                } else {
                    qtdX = 23;
                }

                qtdY++;
            }
        } else {
            int qtdX = 0;
            int qtdY = 0;

            while (qtdY < 50) {
                while (qtdX < 50) {
                    ret += this.matriz[qtdX][qtdY];
                    qtdX++;
                }

                ret += '\n';

                qtdX = 0;
                qtdY++;
            }
        }

        return ret;
    }
}