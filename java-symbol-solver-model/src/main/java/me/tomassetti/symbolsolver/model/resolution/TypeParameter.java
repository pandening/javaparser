package me.tomassetti.symbolsolver.model.resolution;

import me.tomassetti.symbolsolver.model.typesystem.Type;

import java.util.List;

/**
 * @author Federico Tomassetti
 */
public interface TypeParameter {

    static TypeParameter onClass(final String name, String classQName, List<Bound> bounds) {
        return new TypeParameter() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public boolean declaredOnClass() {
                return true;
            }

            @Override
            public boolean declaredOnMethod() {
                return false;
            }

            @Override
            public String qualifiedName() {
                return String.format("%s.%s", classQName, name);
            }

            @Override
            public String getQNameOfDeclaringClass() {
                return classQName;
            }

            @Override
            public List<Bound> getBounds(TypeSolver typeSolver) {
                return bounds;
            }

            @Override
            public String toString() {
                return "TypeParameter onClass " + name;
            }
        };
    }

    String getName();

    boolean declaredOnClass();

    boolean declaredOnMethod();

    String qualifiedName();

    @Deprecated
    String getQNameOfDeclaringClass();

    List<Bound> getBounds(TypeSolver typeSolver);

    class Bound {
        private boolean extendsBound;
        private Type type;

        private Bound(boolean extendsBound, Type type) {
            this.extendsBound = extendsBound;
            this.type = type;
        }

        public static Bound extendsBound(Type type) {
            return new Bound(true, type);
        }

        public static Bound superBound(Type type) {
            return new Bound(false, type);
        }

        public Type getType() {
            return type;
        }

        public boolean isExtends() {
            return extendsBound;
        }

        public boolean isSuper() {
            return !isExtends();
        }
    }

}
