import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

/**
 * Sample tests for ArrayCartesianPlane. Note that these tests are not
 * comprehensive (the marking suite comprises of more tests) and that passing
 * all the tests do NOT guarantee full marks.
 *
 * It is recommended to add more tests (especially for edge cases). This file
 * should NOT be submitted with your assignment.
 */
public class CartesianPlaneTest {
    private CartesianPlane<Integer> plane;

    @Before
    public void setup() {
        plane = new ArrayCartesianPlane<>(0, 5, 0, 5);
    }

    @Test
    public void testValidConstructor() {
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                plane.add(i, j, 1);
            }
        }

        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                if (plane.get(i, j) != 1) {
                    fail();
                }
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConstructor() {
        ArrayCartesianPlane<String> bad = new ArrayCartesianPlane<>(-2, 0, 5,
                0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInvalidGet() {
        plane.get(-2, 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAdd() {
        plane.add(-3, 7, 4);
    }

    @Test
    public void testEdgeCoordAdd() {
        plane.add(5, 5, 2);
        assertEquals(Integer.valueOf(2), plane.get(5, 5));
    }

    @Test
    public void testRemove() {
        plane.add(1, 2, 5);
        assertTrue(plane.remove(1, 2));
    }

    @Test
    public void testRemoveNonElement() {
        assertFalse(plane.remove(1, 2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInvalidRemove() {
        plane.remove(-1, 8);
    }

    @Test
    public void testClear() {
        plane.clear();
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                if (plane.get(i, j) != null) {
                    fail();
                }
            }
        }
    }

    @Test
    public void testValidResize() {
        plane.add(0, 0, 1);
        plane.add(1, 1, 1);
        plane.add(2, 2, 1);
        plane.add(3, 3, 1);
        plane.add(4, 4, 1);
        plane.add(5, 5, 1);
        plane.resize(0, 6, 0, 6);
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 6; j++) {
                if (i == j && i != 6) {
                    if (plane.get(i, j) != 1) {
                        fail();
                    }
                } else {
                    if (plane.get(i, j) != null) {
                        fail();
                    }
                }
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidResizeArgsX() {
        plane.resize(6, 2, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidResizeArgsY() {
        plane.resize(1, 3, 7, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidResizeArgsBoth() {
        plane.resize(7, 1, 7, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidResizeExistingElement() {
        plane.add(3, 3, 3);
        plane.resize(2, 4, 0, 2);
    }
}
